package com.kasina.automobileapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Adjust the mapping pattern as needed
                .allowedOrigins("http://localhost:3000") // Allow requests from this origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                .allowCredentials(true) // Allow cookies, authentication headers, etc.
                .maxAge(3600); // Cache preflight requests for 1 hour (optional)
    }
}
