package org.rivero.todo.entities

import jakarta.persistence.*

@Entity
class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic
        val id: Long,
        val title: String,
        val description: String
) {
}