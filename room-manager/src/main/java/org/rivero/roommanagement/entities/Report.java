package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;
import org.rivero.roommanagement.dtos.ReceiptDto;

import java.util.List;

@Getter
@Setter
public class Report {
    String id;
    List<ReceiptDto> receiptList;
    List<ReceiptDto> paidReceiptList;
    String consumerId;
    int paidTotal;
    int consumedTotal;

    public Report(String id, List<ReceiptDto> receiptList, List<ReceiptDto> paidReceiptList, String consumerId, int paidTotal, int consumedTotal) {
        this.id = id;
        this.receiptList = receiptList;
        this.paidReceiptList = paidReceiptList;
        this.consumerId = consumerId;
        this.paidTotal = paidTotal;
        this.consumedTotal = consumedTotal;
    }
}
