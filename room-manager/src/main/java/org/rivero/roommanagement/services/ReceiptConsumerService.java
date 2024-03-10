package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.ReceiptConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptConsumerService {
    private final ReceiptConsumerRepository receiptConsumerRepository;
    final DBConnectionManager dbConnectionManager;

    public void create(ReceiptConsumer request) {
        receiptConsumerRepository.insert(request);
    }

    public List<ReceiptConsumer> getByUserId(String id) {
        return receiptConsumerRepository.getListByUserId(id);
    }

    public List<ReceiptConsumer> getByReceiptId(String id) {
        return receiptConsumerRepository.getListByReceiptId(id);
    }

    public String deleteOne(String id) {
        receiptConsumerRepository.deleteOne(id);
        return "Record deleted";
    }
}
