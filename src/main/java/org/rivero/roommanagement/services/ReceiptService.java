package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.rivero.roommanagement.repositories.ReceiptConsumerRepository;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class ReceiptService {
    private  final MoneyConsumeEventRepository moneyConsumeEventRepository;
    private final ReceiptConsumerRepository receiptConsumerRepository;
    private  final ReceiptConsumerService receiptConsumerService;
    private  final UserRepository userRepository ;
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();


    public ReceiptDTO getReceiptById(String id) {
        return moneyConsumeEventRepository.getOne(connection, id, receiptConsumerService);
    }

    public List<MoneyConsumeEvent> getAllReceipt() {
        return moneyConsumeEventRepository.getList(connection);
    }

    public void create(ReceiptCreateRequest request) {
        String id = UUID.randomUUID().toString();
        moneyConsumeEventRepository.insert(connection, request, id);
        request.consumerList().forEach(consumer -> {
            receiptConsumerService.create(new ReceiptConsumer(UUID.randomUUID().toString(), id, consumer));
            userRepository.increaseUserDebt(connection, request.moneyAmount() / request.consumerList().size(), consumer);
        });
        userRepository.increaseUserBalance(connection, request.moneyAmount(), request.buyerId());
    }

    public String deleteOne(String id) {
        ArrayList<ReceiptConsumer> rs = receiptConsumerRepository.getListByReceiptId(connection, id);
        rs.forEach(rc -> receiptConsumerRepository.deleteOne(connection, rc.getId()));
        moneyConsumeEventRepository.deleteOne(connection, id);
        return "Record deleted";
    }

    public void updateOne(ReceiptUpdateRequest receipt){
        moneyConsumeEventRepository.updateOne(connection, receipt);
    }
}
