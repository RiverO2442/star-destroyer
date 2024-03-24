package org.rivero.roommanager.receipt;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanager.user.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final UserService userService;

    @GetMapping("/receipts")
    public Mono<Page<ReceiptSummary>> getReceipt(
            @RequestParam(required = false) ZonedDateTime fromDate,
            @RequestParam(required = false) ZonedDateTime toDate,
            @ParameterObject Pageable pageable
    ) {
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
        return receiptService.getAllReceipt(fromDate, toDate, null, pageable);
    }

    @PutMapping("/receipts")
    public ResponseEntity<String> updateReceipt(@RequestBody ReceiptUpdateRequest request) {
        receiptService.updateOne(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/receipts")
    @ResponseStatus(CREATED)
    public Mono<String> addReceipt(
            @RequestBody CreateReceiptRequest request
    ) {
//        UserInfo userInfo = userService.authorize(token);
        return receiptService.create(request);
    }

    @GetMapping("/receipts/{receiptId}")
    public Mono<ReceiptDto> getReceiptById(@PathVariable(name = "receiptId") String receiptId) {
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
        return Mono.empty();
    }

    @DeleteMapping("/receipts/{receiptId}")
    public ResponseEntity<Void> deleteReceiptById(@PathVariable(name = "receiptId") String receiptId) {
        receiptService.deleteOne(receiptId);
        return ResponseEntity.noContent().build();
    }
}
