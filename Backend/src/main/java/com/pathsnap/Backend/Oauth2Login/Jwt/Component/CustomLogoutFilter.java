package com.pathsnap.Backend.Oauth2Login.Jwt.Component;

import com.pathsnap.Backend.Oauth2Login.Repository.RefreshRepository;
import com.pathsnap.Backend.Oauth2Login.Service.RefreshService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RefreshService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 로그아웃 경로와 메서드 확인
        String requestUri = request.getRequestURI();
        System.out.println("2.requestUri = " + requestUri);
        if (!requestUri.matches("^\\/logout$")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("success(b)");

        // Refresh 토큰 추출
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh".equals(cookie.getName())) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        System.out.println("success(c)");
        // Refresh 토큰 유효성 확인
        if (refresh == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("success(d)");
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("success(e)");
        if (!"refresh".equals(jwtUtil.getCategory(refresh))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("success(f)");
        // DB에서 Refresh 토큰 존재 여부 확인
        if (!refreshTokenService.existsByRefresh(refresh)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        System.out.println("success(g)");
        // 로그아웃 처리
        if (refreshTokenService.existsByRefresh(refresh)) {
            refreshTokenService.deleteRefreshToken(refresh);
        } else {
            System.out.println("Refresh token not found: " + refresh);
        }
        System.out.println("Deleting refresh token: " + refresh);
        System.out.println("Token exists in DB: " + refreshTokenService.existsByRefresh(refresh));

        Cookie expiredCookie = new Cookie("refresh", null);
        expiredCookie.setMaxAge(0);
        expiredCookie.setPath("/");
        response.addCookie(expiredCookie);

        System.out.println("success(h)");
        response.sendRedirect("http://pathsnap.shop/register");
        System.out.println("success(k)");
    }
}