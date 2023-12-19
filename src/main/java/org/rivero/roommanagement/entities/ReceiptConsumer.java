package org.rivero.roommanagement.entities;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ReceiptConsumer {
    String id;
    String receiptId;
    String consumerId;
    public ReceiptConsumer(String id, String receiptId, String consumerId){
        this.id = id;
        this.receiptId = receiptId;
        this.consumerId = consumerId;
    }
}
