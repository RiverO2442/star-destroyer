package org.rivero.roommanagement.controllers;

import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDto;
import org.rivero.roommanagement.dtos.UserInfo;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.rivero.roommanagement.services.ReceiptService;
import org.rivero.roommanagement.services.UserService;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    private final UserService userService;

    @GetMapping("/receipts")
    public Page<ReceiptDto> getReceipt(
            @RequestParam(required = false) ZonedDateTime fromDate,
            @RequestParam(required = false) ZonedDateTime toDate
    ) {
//        UserInfo userInfo = userService.authorize(token);
        return new PageImpl<>(receiptService.getAllReceipt(fromDate, toDate, null).stream()
                .map(moneyConsumeEvent -> new ReceiptDto(
                        moneyConsumeEvent.getName(),
                        moneyConsumeEvent.getMoneyAmount(),
                        moneyConsumeEvent.getBuyerId(),
                        moneyConsumeEvent.getConsumers(),
                        moneyConsumeEvent.getId(),
                        moneyConsumeEvent.getDescription(),
                        moneyConsumeEvent.getCreatedDate()

                )).toList());
    }

    @PutMapping("/receipts")
    public ResponseEntity<String> updateReceipt(@RequestBody ReceiptUpdateRequest request) {
        receiptService.updateOne(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/receipts")
    public ResponseEntity<String> addReceipt(
            @RequestBody ReceiptCreateRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        UserInfo userInfo = userService.authorize(token);

        receiptService.create(request, userInfo);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @GetMapping("/receipts/{receiptId}")
    public ResponseEntity<ReceiptDto> getReceiptById(@PathVariable(name = "receiptId") String receiptId) {
        ReceiptDto receipt = receiptService.getReceiptById(receiptId);
        if (receiptService.getReceiptById(receiptId) != null)
            return ResponseEntity.ok().body(new ReceiptDto(
                    receipt.name(),
                    receipt.moneyAmount(),
                    receipt.buyerId(),
                    receipt.consumers(),
                    receipt.id(),
                    receipt.description(),
                    receipt.createDate()
            ));
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
    }

    @DeleteMapping("/receipts/{receiptId}")
    public ResponseEntity<Void> deleteReceiptById(@PathVariable(name = "receiptId") String receiptId) {
        receiptService.deleteOne(receiptId);
        return ResponseEntity.noContent().build();
    }
}
