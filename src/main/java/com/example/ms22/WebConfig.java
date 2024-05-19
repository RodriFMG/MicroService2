package com.example.ms22;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Cambia esto si tu frontend está en otra URL
                .allowedMethods("GET", "POST", "PUT","PATCH" , "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}