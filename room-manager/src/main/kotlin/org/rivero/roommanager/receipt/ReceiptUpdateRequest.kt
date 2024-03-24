package org.rivero.roommanager.receipt

@JvmRecord
data class ReceiptUpdateRequest(
    val id: String,
    val name: String,
    val moneyAmount: Int,
    val buyerId: String,
    val consumerList: List<String>,
    val description: String
)
