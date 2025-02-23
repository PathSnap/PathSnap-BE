package com.pathsnap.Backend.Oauth2Login.Jwt.Component;


import com.pathsnap.Backend.Oauth2Login.Dto.Res.CustomOauth2UserResDto;
import com.pathsnap.Backend.Oauth2Login.Dto.Res.Oauth2UserResDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        System.out.println("2.requestUri =   " + requestUri);
        if (requestUri.startsWith("/swagger-ui") || requestUri.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 특정 경로 필터 제외
        String requestUri1 = request.getRequestURI();
        if (requestUri1.equals("/reissue")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("success(a)");

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {

//            filterChain.doFilter(request, response);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        System.out.println("success(b)");
        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            // 엑세스 토큰이 만료되었으면 클라이언트에 에러 응답 반환
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 상태 코드
            response.setContentType("application/json");  // 응답 Content-Type을 JSON으로 설정

            // JSON 형식으로 에러 메시지 작성
            String errorResponse = "{\"error\": \"Access token expired.\"}";

            response.getWriter().write(errorResponse);  // 응답 바디에 JSON 형태로 에러 메시지 작성
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //토큰에서 userId와 role 획득
        String userId = jwtUtil.getUserId(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //userDTO를 생성하여 값 set
        Oauth2UserResDto oauth2UserResDto = new Oauth2UserResDto();
        oauth2UserResDto.setUserId(userId);
        oauth2UserResDto.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomOauth2UserResDto customOauth2UserResDto = new CustomOauth2UserResDto(oauth2UserResDto);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOauth2UserResDto, null, customOauth2UserResDto.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }


    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("refresh".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}