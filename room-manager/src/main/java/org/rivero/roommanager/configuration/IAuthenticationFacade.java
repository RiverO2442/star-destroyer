package org.rivero.roommanager.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface IAuthenticationFacade {
    Mono<Authentication> getAuthentication();

    Mono<Jwt> getPrincipal();
}
