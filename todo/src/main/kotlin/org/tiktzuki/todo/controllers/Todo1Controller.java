package org.tiktzuki.todo.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiktzuki.todo.entities.Todo;
import org.tiktzuki.todo.repositories.TodoRepository;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/todos")
public class Todo1Controller {
    private final TodoRepository todoRepository;

    public Todo1Controller(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{id}")
    String z(@PathVariable String id) {
        return "hehe";
    }

    @GetMapping
    Mono<Page<Todo>> list(@ParameterObject Pageable pageable) {
        return Mono.just(todoRepository.findAll(pageable));
    }
}
