package org.rivero.roommanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .cors(cors->cors.configurationSource(createCorsConfigSource()))
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
                .authorizeExchange(authorize -> authorize
                        .pathMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .anyExchange().permitAll()
                )
                .oauth2ResourceServer(it -> it.jwt(Customizer.withDefaults()))
                .build();
    }

    CorsConfigurationSource createCorsConfigSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
