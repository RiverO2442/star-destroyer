package org.rivero.roommanager.request;

import java.util.List;

public record ReceiptUpdateRequest(

        String id,
        String name,
        int moneyAmount,
        String buyerId,
        List<String> consumerList,
        String description
) {
}
