package com.aivle.bookapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // 모든 API 경로에 대해
                .allowedOrigins(
                        "http://localhost:5173", // 로컬 환경의 Vite 프론트엔드 주소 허용
                        "http://localhost:3000", // 로컬 환경의 리액트 주소 (필요시 유지)
                        "http://a62c75d8bcd9a4e7eba7c2ad871cfe7c-240072404.us-west-1.elb.amazonaws.com" // 배포된 프론트엔드 주소
                )
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/books/**")
                .excludePathPatterns("/auth/**", "/h2-console/**");
    }
}