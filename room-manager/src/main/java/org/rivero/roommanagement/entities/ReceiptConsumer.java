package org.rivero.roommanagement.entities;

import lombok.Getter;

@Getter
public class ReceiptConsumer {
    String id;
    String receiptId;
    String consumerId;

    public ReceiptConsumer(String id, String receiptId, String consumerId) {
        this.id = id;
        this.receiptId = receiptId;
        this.consumerId = consumerId;
    }
}
