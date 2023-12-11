package org.rivero.roommanagement.controllers;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.services.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReceiptController {
    ReceiptService receiptService = new ReceiptService();

//    @GetMapping("/receipt")
//    public List<MoneyConsumeEvent> getReceipt() {
//        try {
//            return receiptService.getAllReceipt();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @PostMapping("/receipt")
//    public ResponseEntity<MoneyConsumeEvent> addReceipt(@RequestBody Receipt receipt) {
//        try {
//            receiptService.register(receipt);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok().body(receipt);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Receipt receipt){
//        try {
//          return ResponseEntity.ok().body(receiptService.login(receipt));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/receipt/{receiptId}")
//    public ResponseEntity<MoneyConsumeEvent> getReceiptById(@PathVariable(name = "receiptId") String receiptId) {
//        try {
//            if(receiptService.getReceiptById(receiptId) != null)
//                return ResponseEntity.ok().body(receiptService.getReceiptById(receiptId));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok().body(new MoneyConsumeEvent(receiptId, "null", "null", 0));
//    }

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
