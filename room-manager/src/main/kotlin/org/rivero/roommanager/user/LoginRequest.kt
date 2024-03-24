package org.rivero.roommanager.user

@JvmRecord
data class LoginRequest(
    val username: String,
    val password: String
)
