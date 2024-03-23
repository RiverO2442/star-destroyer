package org.rivero.roommanager.user

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class UserController @Autowired constructor(
    val userService: UserService,
    val userRepository: UserRepository
) {

    @GetMapping("/members")
    fun getMembers(): Flux<MetadataItem> {
        return Flux.fromStream(userRepository.findAll().stream())
            .map { it!! }
            .map { u -> MetadataItem(u.id, u.name) }
    }

    @GetMapping("/users")
    fun getUsers(): Flux<User> {
        return Flux.fromStream(userRepository.findAll().stream());
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(): Mono<Any> {
        return userService.register();
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(name = "id") id: String): Mono<UserDto> {
        return Mono.justOrEmpty(userRepository.findById(id))
            .map { it!! }
            .map { u -> UserDto(u.id, u.name) }
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(
        @PathVariable(name = "id") id: String?,
        @RequestBody user: UpdateUserRequest?
    ): ResponseEntity<String> {
        return Mono.just(
            userService.update()
        )
        userService.updateOne(user, id)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserById(@PathVariable(name = "id") id: String) {
        userService.deleteOne(id)
    }
}
