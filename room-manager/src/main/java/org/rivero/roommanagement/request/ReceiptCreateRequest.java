package org.rivero.roommanagement.request;

import java.util.List;

public record ReceiptCreateRequest(
        String name,
        int moneyAmount,
        String buyerId,
        List<String> consumerList,
        String description
) {
}
