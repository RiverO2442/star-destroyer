package org.rivero.roommanager.configuration

import org.rivero.roommanager.EMAIL
import org.rivero.roommanager.NAME
import org.rivero.roommanager.user.AuthUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationFacade : IAuthenticationFacade {
    override val authentication: Mono<Authentication>
        get() = ReactiveSecurityContextHolder.getContext()
            .map { obj: SecurityContext -> obj.authentication }

    override val principal: Mono<Jwt>
        get() = authentication
            .map { obj: Authentication -> obj.principal }
            .cast(Jwt::class.java)

    override val user: Mono<AuthUser>
        get() = authentication
            .map { auth: Authentication ->
                val jwt = auth.principal as Jwt
                return@map AuthUser(
                    id = jwt.claims[EMAIL].toString(),
                    name = jwt.claims[NAME].toString(),
                    email = jwt.claims[EMAIL].toString()
                )
            }
}