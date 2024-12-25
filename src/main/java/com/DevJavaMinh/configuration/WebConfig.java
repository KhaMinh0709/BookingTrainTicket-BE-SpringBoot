package com.DevJavaMinh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Áp dụng CORS cho các URL có tiền tố /api/
                        .allowedOrigins("http://localhost:5173") // Nguồn gốc React App
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Phương thức HTTP cho phép
                        .allowedHeaders("*") // Header cho phép
                        .allowCredentials(true); // Cho phép gửi cookie nếu cần
            }
        };
    }
}
