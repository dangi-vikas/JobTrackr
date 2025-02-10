package com.auth.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI jobTrackrUserOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User API")
                        .description("API documentation for JobTrackr User Service, a job application tracking system.")
                        .version("1.0.0"));
    }
}