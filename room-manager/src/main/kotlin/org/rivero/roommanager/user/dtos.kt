package org.rivero.roommanager.user;

@JvmRecord
data class UpdateUserRequest(
    val name: String,
    val amount: String,
)

@JvmRecord
data class UserDto(
    val id: String,
    val name: String
)

@JvmRecord
data class MetadataItem(
    val id: String,
    val name: String,
)