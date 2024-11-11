package com.pathsnap.Backend.Config;

import com.pathsnap.Backend.Security.Service.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll() // 인증 예외 경로 설정
                        .anyRequest().authenticated()) // 나머지는 인증 필요
                .oauth2Login((oauth2)->oauth2
                        .loginPage("/login/naver")
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(oAuth2UserService))); // OAuth2 로그인 사용
                //.and()
                //.logout(logout -> logout // 로그아웃 설정
                 //       .logoutSuccessUrl("/")); // 로그아웃 후 리디렉션 경로

        return http.build();
    }
}
