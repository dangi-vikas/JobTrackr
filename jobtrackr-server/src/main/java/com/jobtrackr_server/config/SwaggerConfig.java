package com.jobtrackr_server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI jobTrackrOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JobTrackr API")
                        .description("API documentation for JobTrackr, a job application tracking system.")
                        .version("1.0.0"));
    }
}