package org.rivero.roommanager.user

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import jakarta.xml.bind.DatatypeConverter
import lombok.RequiredArgsConstructor
import mu.KLogging
import org.rivero.roommanager.EMAIL
import org.rivero.roommanager.NAME
import org.rivero.roommanager.PICTURE
import org.rivero.roommanager.configuration.IAuthenticationFacade
import org.rivero.roommanager.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.math.BigDecimal
import java.net.URI
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
@RequiredArgsConstructor
class UserService @Autowired constructor(
    val userRepository: UserRepository,
    val authenticationFacade: IAuthenticationFacade,
    val mapper: ObjectMapper,
    val properties: OAuth2ResourceServerProperties
) {
    fun register(): Mono<Any> {
        return authenticationFacade.principal
            .publishOn(Schedulers.boundedElastic())
            .handle<Any> { it, sink ->
                logger.debug { mapper.writeValueAsString(it) }
                val google = properties.jwt.issuerUri
                when (it.issuer.toString()) {
                    google -> {
                        val userBuilder = User.builder()
                            .id(it.claims[EMAIL].toString())
                            .googleId(it.subject)
                            .name(it.claims[NAME].toString())
                            .email(it.claims[EMAIL].toString())
                            .balance(BigDecimal.ZERO)
                        it.claims[PICTURE]?.let {
                            try {
                                userBuilder.picture(URI.create(it.toString()).toURL())
                            } catch (e: Exception) {
                                error(e)
                            }
                        }
                        sink.next(userRepository.save(userBuilder.build()).id)
                    }

                    else -> {
                        sink.error(NotImplementedError("not implemented issuer"))
                    }
                }
            }
    }

    fun login(request: LoginRequest): String {
        throw NotImplementedError()
//        if (DigestUtils.md5DigestAsHex(request.password.toByteArray(StandardCharsets.UTF_8))
//            == userRepository!!.getPasswordHash(request.username)
//        ) {
//            val user = userRepository.getByUsername(request.username)
//                .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "user not found") }
//
//            return generateToken(user).compact()
//        } else throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password doesnt matched")
    }

    protected fun generateToken(user: User): JwtBuilder {
        val keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey: Key = SecretKeySpec(keyBytes, "HmacSHA256")
        return Jwts.builder()
            .subject(user.id)
            .claim("username", user.name)
            .issuedAt(Date(System.currentTimeMillis()))
            .issuer(TOKEN_ISSUER)
            .expiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(signingKey)
    }

    fun deleteOne(id: String) {
        userRepository.deleteById(id)
    }

    fun update(id: String, request: UpdateUserRequest) {
        userRepository.findById(id)
            .map {
                userRepository.save(it)
            }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "user not found") }
    }

    fun authorize(token: String?): UserInfo {
        val keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(keyBytes, "HmacSHA256")
        val jwt = Jwts.parser().verifyWith(signingKey).build().parse(token) as Jwt<Header, Claims>
        return UserInfo(
            jwt.payload.subject,
            jwt.payload.get("username", String::class.java)
        )
    }

    companion object : KLogging() {
        const val TOKEN_ISSUER: String = "room-manager-service"
        const val SECRET_KEY: String =
            "my-secret-zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"
    }
}
