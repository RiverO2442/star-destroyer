package org.tiktzuki.todo.controllers

import mu.KLogging
import org.springdoc.core.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.tiktzuki.todo.dto.CreateTodoRequest
import org.tiktzuki.todo.dto.UpdateTodoRequest
import org.tiktzuki.todo.entities.Todo
import org.tiktzuki.todo.repositories.TodoRepository
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/api/v1/todos")
class TodoController @Autowired constructor(
        val todoRepository: TodoRepository
) {
    companion object : KLogging()

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): Mono<Todo> {
        return todoRepository.findById(id)
                .map { Mono.just(it) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    @GetMapping
    fun list(@ParameterObject pageable: Pageable): Mono<Page<Todo>> {
        return Mono.just(todoRepository.findAll(pageable))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody todoRequest: CreateTodoRequest): Mono<Todo> {
        logger.info { todoRequest }
        val todo = todoRepository.save(Todo(title = todoRequest.title))
        return Mono.just(todo)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun put(@PathVariable id: UUID, @RequestBody updateTodo: UpdateTodoRequest): Mono<Void> {
        todoRepository.findById(id)
                .ifPresentOrElse({
                    it.description = updateTodo.description
                    it.title = updateTodo.title
                    it.completed = updateTodo.completed
                    todoRepository.save(it)
                }, { throw ResponseStatusException(HttpStatus.NOT_FOUND) })
        return Mono.empty()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): Mono<Void> {
        todoRepository.deleteById(id)
        return Mono.empty()
    }
}