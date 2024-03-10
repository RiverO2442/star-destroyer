package org.rivero.roommanagement.dtos;

import java.time.ZonedDateTime;
import java.util.List;

public record ReceiptDto(
        String name,
        int moneyAmount,
        String buyerId,
        List<String> consumers,
        String id,
        String description,
        ZonedDateTime createDate
) {
}
