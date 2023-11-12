package org.tiktzuki.todo.dto

data class CreateTodoRequest(
        val title: String
)

data class UpdateTodoRequest(
        val title: String,
        val completed: Boolean = false,
        val description: String? = null
)