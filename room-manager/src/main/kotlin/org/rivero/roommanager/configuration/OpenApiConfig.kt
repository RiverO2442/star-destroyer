package org.rivero.roommanager.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import lombok.RequiredArgsConstructor
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.List

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
class OpenApiConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securitySchemeName = "bearerAuth"
        val apiKey = "apiKey"
        val basicAuth = "basic"
        return OpenAPI()
            .security(
                List.of(
                    SecurityRequirement().addList(securitySchemeName),
                    SecurityRequirement().addList(apiKey)
                )
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        securitySchemeName, SecurityScheme()
                            .name(securitySchemeName)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
                    .addSecuritySchemes(
                        apiKey, SecurityScheme()
                            .name("api-key")
                            .type(SecurityScheme.Type.APIKEY)
                            .`in`(SecurityScheme.In.HEADER)
                    )
                    .addSecuritySchemes(
                        basicAuth, SecurityScheme()
                            .name(basicAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("basic")
                    )
            )
    }
}
