package org.rivero.roommagement.receipt

import java.math.BigDecimal
import java.net.URL


class Receipt(
        var creatorId: String,
        var consumerIds: Set<String>,
        var imageUrl: URL?,
        var amountToPay: BigDecimal?,
)