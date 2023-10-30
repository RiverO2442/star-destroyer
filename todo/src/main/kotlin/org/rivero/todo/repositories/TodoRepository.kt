package org.rivero.todo.repositories

import org.rivero.todo.entities.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
}