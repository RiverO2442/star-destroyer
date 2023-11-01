package org.tiktzuki.todo.repositories

import org.tiktzuki.todo.entities.Todo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TodoRepository : JpaRepository<Todo, UUID> {
}