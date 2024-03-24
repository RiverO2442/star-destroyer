package org.rivero.roommanager.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.*
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
        return httpSecurity
            .cors { cors: CorsSpec -> cors.configurationSource(createCorsConfigSource()) } //                .csrf(ServerHttpSecurity.CsrfSpec::disable)
            //                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .securityMatcher(PathPatternParserServerWebExchangeMatcher("/**"))
            .authorizeExchange { authorize: AuthorizeExchangeSpec ->
                authorize
                    .pathMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                    .pathMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/*/users/*").permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { it: OAuth2ResourceServerSpec -> it.jwt(Customizer.withDefaults()) }
            .build()
    }

    fun createCorsConfigSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.addAllowedOriginPattern("*")
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")
        config.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}
