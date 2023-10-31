package org.tiktzuki.todo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.tiktzuki.todo.entities.Todo
import org.tiktzuki.todo.repositories.TodoRepository

@RestController
@RequestMapping("/api/v1/todos")
class TodoController @Autowired constructor(
    val todoRepository: TodoRepository
) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Todo> {
        return todoRepository.findById(id)
            .map(::ok)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    @GetMapping
    fun list(pageable: Pageable): ResponseEntity<Page<Todo>> {
        return ok(todoRepository.findAll(pageable))
    }

    @PostMapping
    fun post(@RequestBody todo: Todo): ResponseEntity<Todo> {
        todoRepository.save(todo);
        return ResponseEntity(todo, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Long, todo: Todo): ResponseEntity<Void> {
        todoRepository.findById(id)
            .ifPresentOrElse({
                it.description = todo.description;
                it.title = todo.title
            }, { throw ResponseStatusException(HttpStatus.NOT_FOUND) })
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        todoRepository.deleteById(id)
        return ResponseEntity.noContent().build();
    }
}