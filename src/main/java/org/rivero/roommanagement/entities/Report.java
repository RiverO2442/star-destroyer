package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rivero.roommanagement.dtos.ReceiptDTO;

import java.util.List;

@Getter
@Setter
public class Report {
    String id;
    List<ReceiptDTO> receiptList;
    List<ReceiptDTO> paidReceiptList;
    String consumerId;
    int paidTotal;
    int consumedTotal;
    public Report(String id, List<ReceiptDTO> receiptList, List<ReceiptDTO> paidReceiptList, String consumerId, int paidTotal, int consumedTotal){
        this.id = id;
        this.receiptList = receiptList;
        this.paidReceiptList = paidReceiptList;
        this.consumerId = consumerId;
        this.paidTotal = paidTotal;
        this.consumedTotal = consumedTotal;
    }
}
