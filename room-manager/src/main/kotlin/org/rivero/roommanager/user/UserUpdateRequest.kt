package org.rivero.roommanager.user

@JvmRecord
data class UserUpdateRequest(
    val passwordHash: String,
    val id: String
)
