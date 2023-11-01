package org.tiktzuki.todo.controllers

import mu.KLogging
import org.springdoc.core.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
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
    fun get(@PathVariable id: UUID): ResponseEntity<Todo> {
        return todoRepository.findById(id)
                .map(::ok)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    @GetMapping
    fun list(@ParameterObject pageable: Pageable): Mono<Page<Todo>> {
        return Mono.just(todoRepository.findAll(pageable))
    }

    @PostMapping
    fun post(@RequestBody todoRequest: CreateTodoRequest): ResponseEntity<Todo> {
        logger.info { todoRequest }
        val todo = todoRepository.save(Todo(title = todoRequest.title))
        return ResponseEntity(todo, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: UUID, @RequestBody updateTodo: UpdateTodoRequest): ResponseEntity<Void> {
        todoRepository.findById(id)
                .ifPresentOrElse({
                    it.description = updateTodo.description
                    it.title = updateTodo.title
                    it.completed = updateTodo.completed
                    todoRepository.save(it)
                }, { throw ResponseStatusException(HttpStatus.NOT_FOUND) })
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        todoRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}