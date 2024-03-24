package org.rivero.roommanager.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Mono<Authentication> getAuthentication() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication);
    }

    @Override
    public Mono<Jwt> getPrincipal() {
        return getAuthentication()
                .map(Authentication::getPrincipal)
                .cast(Jwt.class);
    }
}