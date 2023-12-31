package org.rivero.roommanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.ReceiptDTO;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.mapper.ReceiptDTOMapper;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.rivero.roommanagement.services.ReceiptService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {

    private final ReceiptService receiptService;

    @GetMapping("/reports/{userId}")
    public ResponseEntity<Report> getReport(@PathVariable(name = "userId") String receiptId) {
        receiptService.createReport(receiptId);
        return ResponseEntity.ok().body(receiptService.createReport(receiptId));
    }
}
