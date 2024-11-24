package com.pathsnap.Backend.Oauth2Login.Service;

import com.pathsnap.Backend.Oauth2Login.Dto.Res.CustomOauth2UserResDto;
import com.pathsnap.Backend.Oauth2Login.Entity.RefreshEntity;
import com.pathsnap.Backend.Oauth2Login.Jwt.Component.JwtUtil;
import com.pathsnap.Backend.Oauth2Login.Repository.RefreshRepository;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    private final UserRepository userRepository;

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

        //token 발급
        String access = jwtUtil.createJwt("access",userId,role,1000L);
        String refresh = jwtUtil.createJwt("refresh",userId,role,86400000L);
        System.out.println(access);
        System.out.println(refresh);

        //Refresh token 저장
        addRefreshEntity(userId,refresh,8640000l);

        //응답 설정
        response.setHeader("access",access);
        response.addCookie(createCookie("refresh",refresh));
        response.setStatus(HttpStatus.OK.value());

        User1Entity user1Entity = userRepository.findById(userId).get();
        if (user1Entity.isFirstLogin()) {
            // 신규 회원이면 /register로 리다이렉트
            getRedirectStrategy().sendRedirect(request, response, "http://pathsnap.shop/register");
            user1Entity.setFirstLogin(false);
            userRepository.save(user1Entity);
        } else {
            // 기존 회원이면 /로 리다이렉트
            getRedirectStrategy().sendRedirect(request, response, "http://pathsnap.shop");
        }

    }


    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public void addRefreshEntity(String userId, String refresh, Long expiredMs){

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUserName(userId);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
