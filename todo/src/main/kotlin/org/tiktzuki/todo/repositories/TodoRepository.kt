package org.tiktzuki.todo.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.tiktzuki.todo.entities.Todo
import reactor.core.publisher.Mono
import java.util.*

interface TodoRepository : JpaRepository<Todo, UUID> {
}