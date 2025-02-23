package com.pathsnap.Backend.Oauth2Login.Jwt.Component;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey secretKey;
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());

    }

    public String getUserId(String token) {
        token = token.trim().replaceAll("\\s", ""); // 공백 제거
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
    }
    public String getRole (String token){
        token = token.trim().replaceAll("\\s", ""); // 공백 제거
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    public String getCategory(String token){
        token = token.trim().replaceAll("\\s", ""); // 공백 제거
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category",String.class);
    }
    public Boolean isExpired (String token){
        token = token.trim().replaceAll("\\s", ""); // 공백 제거
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt (String category, String userId, String role, Long expiredMs){
        System.out.println("IssuedAt: " + System.currentTimeMillis());
        System.out.println("ExpiredAt: " + (System.currentTimeMillis() + expiredMs));

        return Jwts.builder()
                .claim("category",category)
                .claim("userId", userId)
                .claim("role", role)
                .claim("iat", System.currentTimeMillis())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}

