package org.rivero.roommanagement.receipt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rivero.roommagement.receipt.CreateReceiptCommand;
import org.rivero.roommanagement.receipt.Receipt;
import org.rivero.roommanagement.receipt.ReceiptRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public Receipt createReceipt(CreateReceiptCommand command){
        receiptRepository.save(new Receipt());
    }
}
