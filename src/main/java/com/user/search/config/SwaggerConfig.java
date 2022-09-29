package com.user.search.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Swagger Configuration for Rest playground
 * Playground will not be available for production.
 */
@Configuration
@Profile("!PRD")
public class SwaggerConfig {

    public OpenAPI applicationApiConfig() {
        return new OpenAPI().info(new Info()
                .title("User Search Services")
                .description("Playground to test the provided APIs"));
    }
}
