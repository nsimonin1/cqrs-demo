package org.afrinnov.rnd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Historic Service")
                .description("This service provide endpoints witch help to save all events occurre in the platform")
                .version("v1.0")
        );
    }
}
