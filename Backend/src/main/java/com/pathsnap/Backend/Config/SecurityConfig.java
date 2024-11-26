package com.pathsnap.Backend.Config;

import com.pathsnap.Backend.Oauth2Login.Jwt.Component.CustomLogoutFilter;
import com.pathsnap.Backend.Oauth2Login.Jwt.Component.JwtFilter;
import com.pathsnap.Backend.Oauth2Login.Repository.RefreshRepository;
import com.pathsnap.Backend.Oauth2Login.Service.CustomOAuth2UserService;
import com.pathsnap.Backend.Oauth2Login.Service.CustomSuccessHandler;
import com.pathsnap.Backend.Oauth2Login.Jwt.Component.JwtUtil;
import com.pathsnap.Backend.Oauth2Login.Service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //웹 보안 설정을 활성화
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JwtUtil jwtUtil;
    private final RefreshService refreshService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://back.pathsnap.shop"));
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드 허용
                        configuration.setAllowCredentials(true); // 인증 정보 포함 허용
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
                        configuration.setMaxAge(3600L); // 사전 플라이트 요청 캐싱 시간 설정

                        // 노출할 헤더 설정
                        configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));

                        return configuration;
                    }
                }));


        http
                .csrf(csrf -> csrf.disable());// CSRF 비활성화
        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());



        //로그아웃
        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil,refreshService), LogoutFilter.class);

        //JwtFilter 추가
        http
                .addFilterAfter(new JwtFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/oauth2/authorization/naver") // 로그인 페이지 경로
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)) // CustomOAuth2UserService 설정
                        .successHandler(customSuccessHandler) // 성공 핸들러
                );


        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger 경로 허용
                        .anyRequest().authenticated());


        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
