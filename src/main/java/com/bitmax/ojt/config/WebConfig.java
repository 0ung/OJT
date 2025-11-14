package com.bitmax.ojt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 📌 CORS 설정 파일
 *
 * 프론트(React) → Nginx → Spring Boot API 호출 시
 * 브라우저에서 "CORS policy" 에러가 발생하는 것을 방지하는 설정.
 *
 * ✔ 모든 출처 허용 ("*")
 * ✔ 모든 메서드 허용 (GET, POST, PUT, DELETE 등)
 * ✔ 모든 헤더 허용
 * ✔ 인증정보 허용(true)
 * ✔ Preflight 요청(cache) 1시간 설정
 *
 * 신입이 반드시 알아야 할 것:
 * - 프론트는 포트(3000), 백엔드는 다른 포트(8080)라서 "Cross-Origin 요청"이 됨
 * - 이때 브라우저가 OPTIONS preflight 요청을 보내기 때문에 서버에서 허용해줘야 함
 */

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")                // 📌 모든 URL 패턴 허용
                        .allowedOrigins("*")             // 📌 모든 출처 허용 (React, Nginx 등)
                        .allowedMethods("GET", "POST",
                                "PUT", "DELETE",
                                "OPTIONS")       // 📌 모든 HTTP 메서드 허용
                        .allowedHeaders("*")             // 📌 모든 헤더 허용
                        .allowCredentials(false)         // 📌 쿠키/세션 사용 안 할 때 false
                        .maxAge(3600);                   // 📌 Preflight 응답 캐시 1시간
            }
        };
    }
}
