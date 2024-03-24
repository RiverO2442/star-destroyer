package org.rivero.roommanager.report

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.entities.Report
import org.rivero.roommanager.receipt.ReceiptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReportController @Autowired constructor(
    val receiptService: ReceiptService
) {

    @GetMapping("/reports/{userId}")
    fun getReport(@PathVariable(name = "userId") userId: String?): ResponseEntity<Report?> {
        return ResponseEntity.ok().body(receiptService.createReport(userId))
    }
}