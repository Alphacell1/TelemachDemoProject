package hr.tomislav.planinic.telemach.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing the OpenAPI/Swagger documentation.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a custom OpenAPI specification, including basic auth security.
     *
     * @return an OpenAPI instance describing this application's API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Footprint Service API")
                        .version("v1.0")
                        .description("Simple REST service for a fictional telco operator."))
                .components(new Components()
                        .addSecuritySchemes("basicAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }

    /**
     * Groups and exposes the public APIs ("/api/**") in a dedicated group.
     *
     * @return a GroupedOpenApi configuration for "public" endpoints
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }
}
