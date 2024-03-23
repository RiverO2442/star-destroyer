package org.rivero.roommanager.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanager.entities.Report;
import org.rivero.roommanager.receipt.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {

    private final ReceiptService receiptService;

    @GetMapping("/reports/{userId}")
    public ResponseEntity<Report> getReport(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok().body(receiptService.createReport(userId));
    }
}