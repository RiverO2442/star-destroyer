package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.mapper.ReceiptDTOMapper;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReceiptService {
    private  final MoneyConsumeEventRepository moneyConsumeEventRepository;
    private  final UserRepository userRepository ;
    private final ReceiptDTOMapper receiptDTOMapper;

    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();


    public ReceiptDTO getReceiptById(String id) {
        return moneyConsumeEventRepository.getOne(connection, id);
    }

    public List<MoneyConsumeEvent> getAllReceipt(String fromDate,String  toDate) {
        if(fromDate != null && toDate != null)
            return moneyConsumeEventRepository.getList(connection, fromDate, toDate);
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
        List<ReceiptConsumer> rs = moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(connection, id);
        rs.forEach(rc -> moneyConsumeEventRepository.deleteOneReceiptConsumer(connection, rc.getId()));
        moneyConsumeEventRepository.deleteOne(connection, id);
    }

    public void updateOne(ReceiptUpdateRequest receipt){
        moneyConsumeEventRepository.updateOne(connection, receipt);
    }

    public void createReceiptConsumer(ReceiptConsumer request) {
        moneyConsumeEventRepository.insertReceiptConsumer(connection, request);
    }

    public List<ReceiptConsumer> getByUserId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByUserId(connection, id);
    }

    public List<ReceiptConsumer> getByReceiptId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(connection, id);
    }

    public String deleteOneRC(String id) {
        moneyConsumeEventRepository.deleteOneReceiptConsumer(connection, id);
        return "Record deleted";
    }

    public Report createReport(String userId){
        List<ReceiptConsumer> receiptConsumers = moneyConsumeEventRepository.getListReceiptConsumerByUserId(connection, userId);
        List<ReceiptDTO> consumedList = new ArrayList<>();
        receiptConsumers.forEach(item -> {
            ReceiptDTO result = moneyConsumeEventRepository.getOne(connection, item.getReceiptId());
            this.getByReceiptId(result.id()).forEach(data -> {
                result.consumerList().add(data.getConsumerId());
            });
            consumedList.add(result);
        });
        List<MoneyConsumeEvent> paidList = moneyConsumeEventRepository.getListByUserId(connection, userId);
        User user = userRepository.getOne(connection, userId);
        return new Report(UUID.randomUUID().toString(),  consumedList, paidList.stream().map(receiptDTOMapper).collect(Collectors.toList()), userId, user.getBalance(), user.getDebt());
    }
}