package org.tiktzuki.todo.entities

import jakarta.persistence.*

@Entity
class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic
        val id: Long,
        var title: String,
        var description: String
) {
}