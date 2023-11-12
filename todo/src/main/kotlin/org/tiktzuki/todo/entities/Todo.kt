package org.tiktzuki.todo.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: UUID? = null,
        var title: String,
        var description: String? = null,
        var completed: Boolean = false,
) {
}