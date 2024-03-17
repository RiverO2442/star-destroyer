package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.UserInfo;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.rivero.roommanagement.repositories.ReceiptRepository;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import receipt.ReceiptDto;
import receipt.ReceiptSummary;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReceiptService {
    private final MoneyConsumeEventRepository moneyConsumeEventRepository;
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;

    public ReceiptDto getReceiptById(String id) {
        return receiptRepository.findById(id)
                .map(ReceiptDto::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Mono<Page<ReceiptSummary>> getAllReceipt(ZonedDateTime fromDate, ZonedDateTime toDate, UserInfo userInfo, Pageable pageable) {
//        if (fromDate != null && toDate != null)
//            return moneyConsumeEventRepository.getList(fromDate, toDate, userInfo.id());
//        return moneyConsumeEventRepository.getList(fromDate, toDate, userInfo.id());
        return Mono.just(receiptRepository.findAll(pageable).map(ReceiptSummary::new));
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
        return null;
//        List<ReceiptConsumer> receiptConsumers = moneyConsumeEventRepository.getListReceiptConsumerByUserId(userId);
//        List<ReceiptDto> consumedList = new ArrayList<>();
//        receiptConsumers.forEach(item -> {
//            ReceiptDto result = moneyConsumeEventRepository.getOne(item.getReceiptId());
//            this.getByReceiptId(result.id()).forEach(data -> {
//                result.consumers().add(data.getConsumerId());
//            });
//            consumedList.add(result);
//        });
//        List<Receipt> paidList = moneyConsumeEventRepository.getListByUserId(userId);
//        User user = userRepository.getOne(userId);
//        return new Report(UUID.randomUUID().toString(), consumedList, paidList.stream()
//                .map(receipt -> new ReceiptDto(
//                        receipt.getName(),
//                        receipt.getMoneyAmount(),
//                        receipt.getBuyerId(),
//                        receipt.getConsumers(),
//                        receipt.getId(),
//                        receipt.getDescription(),
//                        receipt.getCreatedDate())
//                )
//                .toList(), userId, user.getBalance(), user.getDebt());
    }
}