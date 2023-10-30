package org.rivero.todo.controllers

import org.rivero.todo.entities.Todo
import org.rivero.todo.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/todos")
class TodoController @Autowired constructor(
        val todoRepository: TodoRepository
) {

    @GetMapping
    fun list(pageable: Pageable): ResponseEntity<Page<Todo>> {
        return ResponseEntity.ok(todoRepository.findAll(pageable));
    }

    @PostMapping
    fun post(todo: Todo): ResponseEntity<Todo> {
        todoRepository.save(todo);
        return ResponseEntity(todo, HttpStatus.CREATED);
    }
}