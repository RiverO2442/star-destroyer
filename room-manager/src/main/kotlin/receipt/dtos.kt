package receipt

import org.rivero.roommanagement.entities.Receipt
import java.math.BigDecimal
import java.time.ZonedDateTime

@JvmRecord
data class ReceiptDto(
    val id: String,
    val name: String,
    val moneyAmount: BigDecimal,
    val buyerId: String,
    val consumers: List<String>,
    val description: String,
    val createDate: ZonedDateTime
) {
    constructor(receipt: Receipt) : this(
        id = receipt.id,
        name = receipt.name,
        moneyAmount = receipt.moneyAmount,
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
    val moneyAmount: BigDecimal,
    val createDate: ZonedDateTime
) {
    constructor(receipt: Receipt) : this(
        id = receipt.id,
        name = receipt.name,
        moneyAmount = receipt.moneyAmount,
        createDate = receipt.createdDate
    )
}