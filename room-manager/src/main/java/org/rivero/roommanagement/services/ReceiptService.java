package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDto;
import org.rivero.roommanagement.dtos.UserInfo;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReceiptService {
    private final MoneyConsumeEventRepository moneyConsumeEventRepository;
    private final UserRepository userRepository;

    public ReceiptDto getReceiptById(String id) {
        return moneyConsumeEventRepository.getOne(id);
    }

    public List<MoneyConsumeEvent> getAllReceipt(ZonedDateTime fromDate, ZonedDateTime toDate, UserInfo userInfo) {
        if (fromDate != null && toDate != null)
            return moneyConsumeEventRepository.getList(fromDate, toDate, userInfo.id());
        return moneyConsumeEventRepository.getList(fromDate, toDate, userInfo.id());
    }

    public void create(ReceiptCreateRequest request, UserInfo userInfo) {
        String id = UUID.randomUUID().toString();
        moneyConsumeEventRepository.insert(request, id);
        request.consumerList().forEach(consumer -> {
            this.createReceiptConsumer(new ReceiptConsumer(UUID.randomUUID().toString(), id, consumer));
            userRepository.increaseUserDebt(request.moneyAmount() / request.consumerList().size(), consumer, userInfo);
        });
        userRepository.increaseUserBalance(request.moneyAmount(), request.buyerId());
    }

    public void deleteOne(String id) {
        List<ReceiptConsumer> rs = moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(id);
        rs.forEach(rc -> moneyConsumeEventRepository.deleteOneReceiptConsumer(rc.getId()));
        moneyConsumeEventRepository.deleteOne(id);
    }

    public void updateOne(ReceiptUpdateRequest receipt) {
        moneyConsumeEventRepository.updateOne(receipt);
    }

    public void createReceiptConsumer(ReceiptConsumer request) {
        moneyConsumeEventRepository.insertReceiptConsumer(request);
    }

    public List<ReceiptConsumer> getByUserId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByUserId(id);
    }

    public List<ReceiptConsumer> getByReceiptId(String id) {
        return moneyConsumeEventRepository.getListReceiptConsumerByReceiptId(id);
    }

    public String deleteOneRC(String id) {
        moneyConsumeEventRepository.deleteOneReceiptConsumer(id);
        return "Record deleted";
    }

    public Report createReport(String userId) {
        List<ReceiptConsumer> receiptConsumers = moneyConsumeEventRepository.getListReceiptConsumerByUserId(userId);
        List<ReceiptDto> consumedList = new ArrayList<>();
        receiptConsumers.forEach(item -> {
            ReceiptDto result = moneyConsumeEventRepository.getOne(item.getReceiptId());
            this.getByReceiptId(result.id()).forEach(data -> {
                result.consumers().add(data.getConsumerId());
            });
            consumedList.add(result);
        });
        List<MoneyConsumeEvent> paidList = moneyConsumeEventRepository.getListByUserId(userId);
        User user = userRepository.getOne(userId);
        return new Report(UUID.randomUUID().toString(), consumedList, paidList.stream()
                .map(moneyConsumeEvent -> new ReceiptDto(
                        moneyConsumeEvent.getName(),
                        moneyConsumeEvent.getMoneyAmount(),
                        moneyConsumeEvent.getBuyerId(),
                        moneyConsumeEvent.getConsumers(),
                        moneyConsumeEvent.getId(),
                        moneyConsumeEvent.getDescription(),
                        moneyConsumeEvent.getCreatedDate())
                )
                .toList(), userId, user.getBalance(), user.getDebt());
    }
}