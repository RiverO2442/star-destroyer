package org.rivero.roommagement.receipt

import java.math.BigDecimal
import java.net.URL

data class CreateReceiptCommand(
        val creatorId: String,
        val consumerIds: List<String>,
        val imageUrl: URL,
        val totalAmountToPay: BigDecimal
)