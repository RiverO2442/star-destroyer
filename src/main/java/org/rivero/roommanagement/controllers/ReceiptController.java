package org.rivero.roommanagement.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.mapper.ReceiptDTOMapper;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.rivero.roommanagement.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptDTOMapper receiptDTOMapper;

    @GetMapping("/receipts")
    public List<ReceiptDTO> getReceipt(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate) {
        return receiptService.getAllReceipt(fromDate, toDate).stream().map(receiptDTOMapper).collect(Collectors.toList());
    }

    @PutMapping("/receipts")
    public ResponseEntity<String> updateReceipt(@RequestBody ReceiptUpdateRequest request){
        receiptService.updateOne(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/receipts")
    public ResponseEntity<String> addReceipt(@RequestBody ReceiptCreateRequest request) {
        receiptService.create(request);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @GetMapping("/receipts/{receiptId}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable(name = "receiptId") String receiptId) {
        ReceiptDTO receipt = receiptService.getReceiptById(receiptId);
        if (receiptService.getReceiptById(receiptId) != null)
            return ResponseEntity.ok().body(new ReceiptDTO(
                    receipt.name(),
                    receipt.moneyAmount(),
                    receipt.buyerId(),
                    receipt.consumerList(),
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
