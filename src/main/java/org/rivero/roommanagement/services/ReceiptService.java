package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
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
    private  final UserRepository userRepository ;
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();


    public ReceiptDTO getReceiptById(String id) {
        return moneyConsumeEventRepository.getOne(connection, id, this);
    }

    public List<MoneyConsumeEvent> getAllReceipt() {
        return moneyConsumeEventRepository.getList(connection);
    }

    public void create(ReceiptCreateRequest request) {
        String id = UUID.randomUUID().toString();
        moneyConsumeEventRepository.insert(connection, request, id);
        request.consumerList().forEach(consumer -> {
            this.createReceiptConsumer(new ReceiptConsumer(UUID.randomUUID().toString(), id, consumer));
            userRepository.increaseUserDebt(connection, request.moneyAmount() / request.consumerList().size(), consumer);
        });
        userRepository.increaseUserBalance(connection, request.moneyAmount(), request.buyerId());
    }

    public void deleteOne(String id) {
        ArrayList<ReceiptConsumer> rs = moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(connection, id);
        rs.forEach(rc -> moneyConsumeEventRepository.deleteOneReceiptConsumer(connection, rc.getId()));
        moneyConsumeEventRepository.deleteOne(connection, id);
    }

    public void updateOne(ReceiptUpdateRequest receipt){
        moneyConsumeEventRepository.updateOne(connection, receipt);
    }

    public void createReceiptConsumer(ReceiptConsumer request) {
        moneyConsumeEventRepository.insertReceiptConsumer(connection, request);
    }

    public ArrayList<ReceiptConsumer> getByUserId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByUserId(connection, id);
    }

    public ArrayList<ReceiptConsumer> getByReceiptId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(connection, id);
    }

    public String dealeteOne(String id) {
        moneyConsumeEventRepository.deleteOneReceiptConsumer(connection, id);
        return "Record deleted";
    }
}
