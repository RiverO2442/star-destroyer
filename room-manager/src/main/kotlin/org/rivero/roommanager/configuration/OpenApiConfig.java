package org.rivero.roommanager.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiKey = "apiKey";
        final String basicAuth = "basic";
        return new OpenAPI()
                .security(List.of(
                        new SecurityRequirement().addList(securitySchemeName),
                        new SecurityRequirement().addList(apiKey)
                ))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSecuritySchemes(apiKey, new SecurityScheme()
                                .name("api-key")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes(basicAuth, new SecurityScheme()
                                .name(basicAuth)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic"))
                );
    }
}
