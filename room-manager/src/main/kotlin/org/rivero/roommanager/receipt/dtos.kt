package org.rivero.roommanager.receipt

import org.rivero.roommanager.entities.Receipt
import java.math.BigDecimal
import java.time.ZonedDateTime

@JvmRecord
data class CreateReceiptRequest(
    val name: String,
    val amount: BigDecimal,
    val consumers: Array<String>,
    val description: String
)

@JvmRecord
data class ReceiptDto(
    val id: String,
    val name: String,
    val moneyAmount: BigDecimal,
    val buyerId: String,
    val consumers: Array<String>,
    val description: String,
    val createDate: ZonedDateTime
) {
    constructor(receipt: Receipt) : this(
        id = receipt.id,
        name = receipt.name,
        moneyAmount = receipt.amount,
        buyerId = receipt.buyerId,
        consumers = receipt.consumers,
        description = receipt.description,
        createDate = receipt.createdDate
    )
}

@JvmRecord
data class ReceiptSummary(
    val id: String,
    val name: String,
    val amount: BigDecimal,
    val createDate: ZonedDateTime
) {
    constructor(receipt: Receipt) : this(
        id = receipt.id,
        name = receipt.name,
        amount = receipt.amount,
        createDate = receipt.createdDate
    )
}