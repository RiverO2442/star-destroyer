package org.rivero.roommanager.receipt

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.user.UserService
import org.springdoc.core.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReceiptController @Autowired constructor(
    val receiptService: ReceiptService,
    val userService: UserService
) {

    @GetMapping("/receipts")
    fun getReceipt(
        @RequestParam(required = false) fromDate: ZonedDateTime?,
        @RequestParam(required = false) toDate: ZonedDateTime?,
        @ParameterObject pageable: Pageable?
    ): Mono<Page<ReceiptSummary>> {
//        UserInfo userInfo = userService.authorize(token);
//        return new PageImpl<>(receiptService.getAllReceipt(fromDate, toDate, null).stream()
//                .map(moneyConsumeEvent -> new ReceiptDto(
//                        moneyConsumeEvent.getName(),
//                        moneyConsumeEvent.getMoneyAmount(),
//                        moneyConsumeEvent.getBuyerId(),
//                        moneyConsumeEvent.getConsumers(),
//                        moneyConsumeEvent.getId(),
//                        moneyConsumeEvent.getDescription(),
//                        moneyConsumeEvent.getCreatedDate()
//
//                )).toList());
        return receiptService.getAllReceipt(fromDate, toDate, null, pageable!!)
    }

    @PutMapping("/receipts")
    fun updateReceipt(@RequestBody request: ReceiptUpdateRequest?): ResponseEntity<String> {
        receiptService.updateOne(request!!)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/receipts")
    @ResponseStatus(HttpStatus.CREATED)
    fun addReceipt(
        @RequestBody request: CreateReceiptRequest?
    ): Mono<String> {
//        UserInfo userInfo = userService.authorize(token);
        return receiptService.create(request)
    }

    @GetMapping("/receipts/{receiptId}")
    fun getReceiptById(@PathVariable(name = "receiptId") receiptId: String?): Mono<ReceiptDto> {
//        ReceiptDto receipt = receiptService.getReceiptById(receiptId);
//        if (receiptService.getReceiptById(receiptId) != null)
//            return ResponseEntity.ok().body(new ReceiptDto(
//                    receipt.name(),
//                    receipt.amount(),
//                    receipt.buyerId(),
//                    receipt.consumers(),
//                    receipt.id(),
//                    receipt.description(),
//                    receipt.createDate()
//            ));
        return Mono.empty()
    }

    @DeleteMapping("/receipts/{receiptId}")
    fun deleteReceiptById(@PathVariable(name = "receiptId") receiptId: String?): ResponseEntity<Void> {
        receiptService.deleteOne(receiptId)
        return ResponseEntity.noContent().build()
    }
}
