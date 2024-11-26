package com.pathsnap.Backend.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "AuthToken", // 인증 스키마 이름
        type = SecuritySchemeType.APIKEY, // API Key 인증 방식
        in = SecuritySchemeIn.HEADER, // 헤더로 인증 토큰 전달
        paramName = "access" // 헤더 이름을 "access"로 설정
)
@OpenAPIDefinition(security = @SecurityRequirement(name = "AuthToken"))
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("PathSnap Swagger")
                .description("PathSnap REST API")
                .version("1.0.0");
    }
}
