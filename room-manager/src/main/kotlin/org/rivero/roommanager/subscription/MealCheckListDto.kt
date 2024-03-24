package org.rivero.roommanager.subscription

import java.time.ZonedDateTime

@JvmRecord
data class MealCheckListDto(
    val id: String,
    val month: String,
    val checkList: String,
    val consumerId: String,
    val time: ZonedDateTime
)
