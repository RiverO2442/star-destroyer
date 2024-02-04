package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.ReceiptConsumerRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ReceiptConsumerService {
    private final ReceiptConsumerRepository receiptConsumerRepository;
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    public void create(ReceiptConsumer request) {
        receiptConsumerRepository.insert(connection, request);
    }

    public ArrayList<ReceiptConsumer> getByUserId(String id) {
        return receiptConsumerRepository.getListByUserId(connection, id);
    }

    public ArrayList<ReceiptConsumer> getByReceiptId(String id) {
        return receiptConsumerRepository.getListByReceiptId(connection, id);
    }

    public String dealeteOne(String id) {
        receiptConsumerRepository.deleteOne(connection, id);
        return "Record deleted";
    }
}
