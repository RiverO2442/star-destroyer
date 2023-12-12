package org.rivero.roommanagement.controllers;

import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.mapper.ReceiptDTOMapper;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.services.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ReceiptController {
    ReceiptService receiptService = new ReceiptService();
    ReceiptDTOMapper receiptDTOMapper = new ReceiptDTOMapper();

    @GetMapping("/receipt")
    public List<ReceiptDTO> getReceipt() {
        try {
            return receiptService.getAllReceipt().stream().map(receiptDTOMapper).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/receipt")
    public ResponseEntity<String> addReceipt(@RequestBody ReceiptCreateRequest request) {
        try {
            String rs = receiptService.create(request);
            if(rs != null){
                return ResponseEntity.ok().body(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(null);
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Receipt receipt){
//        try {
//          return ResponseEntity.ok().body(receiptService.login(receipt));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable(name = "receiptId") String receiptId) {
        try {
            MoneyConsumeEvent receipt = receiptService.getReceiptById(receiptId);
            if(receiptService.getReceiptById(receiptId) != null)
                return ResponseEntity.ok().body(new ReceiptDTO(
                        receipt.getName(),
                        receipt.getMoneyAmount(),
                        receipt.getBuyerId(),
                        receipt.getConsumerList(),
                        receipt.getId(),
                        receipt.getDescription()
                ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(null);
    }

//    @PutMapping("/receipt/{receiptId}")
//    public ResponseEntity<Void> updateReceiptById(@PathVariable(name = "receiptId") Integer receiptId, @RequestBody Receipt receipt) {
//        receiptList.remove(receiptId.intValue());
//        receiptList.add(receiptId, receipt);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/receipt/{receiptId}")
//    public ResponseEntity<Void> deleteReceiptById(@PathVariable(name = "receiptId") Integer receiptId) {
//        receiptList.remove(receiptId.intValue());
//        return ResponseEntity.noContent().build();
//    }
}
