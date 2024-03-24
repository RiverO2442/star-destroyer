package org.rivero.roommanager.subscription

import java.time.ZonedDateTime

@JvmRecord
data class MealCreateRequest(
    val month: String,
    val consumerId: String,
    val checklist: String,
    val time: ZonedDateTime

)
