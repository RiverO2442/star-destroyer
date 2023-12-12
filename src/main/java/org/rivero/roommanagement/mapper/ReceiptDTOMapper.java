package org.rivero.roommanagement.mapper;

import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ReceiptDTOMapper implements Function<MoneyConsumeEvent, ReceiptDTO> {
    @Override
    public ReceiptDTO apply(MoneyConsumeEvent receipt){
        return new ReceiptDTO(
                receipt.getName(),
                receipt.getMoneyAmount(),
                receipt.getBuyerId(),
                receipt.getConsumerList(),
                receipt.getId(),
                receipt.getDescription()
        );
    }
}
