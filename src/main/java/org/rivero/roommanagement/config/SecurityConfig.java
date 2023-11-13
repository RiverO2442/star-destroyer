package org.rivero.roommanagement.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors(Customizer.withDefaults())
//                .csrf(CsrfConfigurer::disable)
//                .authorizeHttpRequests(registry ->
//                        registry.anyRequest()
//                                .authenticated()
//                );
//        http.oauth2Login(AbstractAuthenticationFilterConfigurer::permitAll);
//        http.logout(httpSecurityLogoutConfigurer ->
//                httpSecurityLogoutConfigurer
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutSuccessUrl("/")
//        );
//        return http.build();
//    }

}