package org.rivero.roommanager.configuration

import org.rivero.roommanager.user.AuthUser
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.Jwt
import reactor.core.publisher.Mono

interface IAuthenticationFacade {
    val authentication: Mono<Authentication>

    val principal: Mono<Jwt>

    val user: Mono<AuthUser>
}
