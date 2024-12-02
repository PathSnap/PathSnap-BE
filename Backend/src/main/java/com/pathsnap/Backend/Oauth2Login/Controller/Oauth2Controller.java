package com.pathsnap.Backend.Oauth2Login.Controller;

import com.pathsnap.Backend.Oauth2Login.Dto.Res.ReissueResDto;
import com.pathsnap.Backend.Oauth2Login.Entity.RefreshEntity;
import com.pathsnap.Backend.Oauth2Login.Jwt.Component.JwtUtil;
import com.pathsnap.Backend.Oauth2Login.Repository.RefreshRepository;
import com.pathsnap.Backend.Oauth2Login.Service.CustomSuccessHandler;
import com.pathsnap.Backend.Oauth2Login.Service.RefreshService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
@ResponseBody
@RequiredArgsConstructor
public class Oauth2Controller {

    private final JwtUtil jwtUtil;
    private final RefreshService refreshService;
    private final CustomSuccessHandler customSuccessHandler;

    @Operation(summary = "토큰 재발급", description = "토큰 재발급", security = @SecurityRequirement(name = "AuthToken"))
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;

        System.out.println("A");

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        System.out.println("AA");

        if (refresh == null) {
            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshService.existsByRefresh(refresh);
        if (!isExist) {
            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // 사용자 ID 추출 시 예외 처리 추가
        String userId;
        try {
            userId = jwtUtil.getUserId(refresh);  // 여기에서 MalformedJwtException이 발생할 수 있습니다.
        } catch (MalformedJwtException e) {
            // JWT 형식이 잘못되었을 경우
            return new ResponseEntity<>("Invalid JWT format", HttpStatus.BAD_REQUEST);
        }

        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", userId, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", userId, role, 8640000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshService.deleteRefreshToken(refresh);
        customSuccessHandler.addRefreshEntity(userId, newRefresh, 86400000L);
        System.out.println("Deleting refresh token: " + refresh);
        System.out.println("Token exists in DB before delete: " + refreshService.existsByRefresh(refresh));

        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        System.out.println("new response access = " + response.getHeader("access"));


        return ResponseEntity.ok(new ReissueResDto(newAccess, newRefresh));
    }

    private Cookie createCookie(String key, String value){

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "None"); // 크로스도메인 전송 허용

        return cookie;
    }
}

