package org.rivero.roommanagement.mapper;

import org.rivero.roommanagement.dtos.ReceiptDto;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReceiptDTOMapper implements Function<MoneyConsumeEvent, ReceiptDto> {
    public ReceiptDto apply(MoneyConsumeEvent receipt) {
        return new ReceiptDto(
                receipt.getName(),
                receipt.getMoneyAmount(),
                receipt.getBuyerId(),
                receipt.getConsumerList(),
                receipt.getId(),
                receipt.getDescription(),
                receipt.getCreateddate()
        );
    }
}
