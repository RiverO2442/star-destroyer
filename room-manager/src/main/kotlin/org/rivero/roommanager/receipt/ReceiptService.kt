package org.rivero.roommanager.receipt

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.rivero.roommanager.configuration.IAuthenticationFacade
import org.rivero.roommanager.dtos.UserInfo
import org.rivero.roommanager.entities.Receipt
import org.rivero.roommanager.entities.ReceiptConsumer
import org.rivero.roommanager.entities.Report
import org.rivero.roommanager.repositories.MoneyConsumeEventRepository
import org.rivero.roommanager.repositories.UserRepository1
import org.rivero.roommanager.request.ReceiptUpdateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.ZonedDateTime
import java.util.function.Consumer

@RequiredArgsConstructor
@Service
@Slf4j
class ReceiptService @Autowired constructor(
    val moneyConsumeEventRepository: MoneyConsumeEventRepository,
    val userRepository: UserRepository1,
    val receiptRepository: ReceiptRepository,
    val authenticationFacade: IAuthenticationFacade,
    val mapper: ObjectMapper
) {

    fun getReceiptById(id: String): ReceiptDto {
        return receiptRepository.findById(id)
            .map { receipt -> ReceiptDto(receipt!!) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Receipt not found") }
    }

    fun getAllReceipt(
        fromDate: ZonedDateTime?,
        toDate: ZonedDateTime?,
        userInfo: UserInfo?,
        pageable: Pageable
    ): Mono<Page<ReceiptSummary>> {
        return Mono.just(receiptRepository.findAll(pageable)
            .map { receipt: Receipt? -> ReceiptSummary(receipt!!) })
    }

    fun create(request: CreateReceiptRequest?): Mono<String> {
        return authenticationFacade.principal
            .publishOn(Schedulers.boundedElastic())
            .map {
                val userID = it.subject
                return@map receiptRepository.save(
                    Receipt.builder()
                        .name(request!!.name)
                        .description(request.description)
                        .amount(request.amount)
                        .consumers(request.consumers)
                        .buyerId(userID)
                        .createdDate(ZonedDateTime.now())
                        .build()
                ).id
            }
            .log()
    }

    fun deleteOne(id: String?) {
        val rs: List<ReceiptConsumer> = moneyConsumeEventRepository!!.getListReceiptConsumerByReceiptId(id)
        rs.forEach(Consumer { rc: ReceiptConsumer -> moneyConsumeEventRepository.deleteOneReceiptConsumer(rc.id) })
        moneyConsumeEventRepository.deleteOne(id)
    }

    fun updateOne(receipt: ReceiptUpdateRequest?) {
        moneyConsumeEventRepository.updateOne(receipt)
    }

    fun createReceiptConsumer(request: ReceiptConsumer?) {
        moneyConsumeEventRepository!!.insertReceiptConsumer(request)
    }

    fun getByUserId(id: String?): List<ReceiptConsumer> {
        return moneyConsumeEventRepository!!.getListReceiptConsumerByUserId(id)
    }

    fun getByReceiptId(id: String?): List<ReceiptConsumer> {
        return moneyConsumeEventRepository!!.getListReceiptConsumerByReceiptId(id)
    }

    fun deleteOneRC(id: String?): String {
        moneyConsumeEventRepository!!.deleteOneReceiptConsumer(id)
        return "Record deleted"
    }

    fun createReport(userId: String?): Report? {
        return null
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