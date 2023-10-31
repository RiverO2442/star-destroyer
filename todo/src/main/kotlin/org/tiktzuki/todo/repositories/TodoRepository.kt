package org.tiktzuki.todo.repositories

import org.tiktzuki.todo.entities.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
}