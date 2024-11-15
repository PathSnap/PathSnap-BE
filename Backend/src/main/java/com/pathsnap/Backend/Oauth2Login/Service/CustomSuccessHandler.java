package com.pathsnap.Backend.Oauth2Login.Service;

import com.pathsnap.Backend.Oauth2Login.Dto.Res.CustomOauth2UserResDto;
import com.pathsnap.Backend.Oauth2Login.Jwt.Component.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //인증된 사용자 정보 가져오기
        CustomOauth2UserResDto customUserDetails = (CustomOauth2UserResDto) authentication.getPrincipal();

        String userId = customUserDetails.getUserId();

        //사용자의 권한 목록 가져오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        //GrantedAuthority에서 첫번째 권한을 role 변수에 저장
        String role = auth.getAuthority();

        //jwt token 생성
        String token = jwtUtil.createJwt(userId, role, 604800L);
        System.out.println("token = "+ token);
        //jwt token을 담은 cookie 생성후 응답에 추가
        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:3000/");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
