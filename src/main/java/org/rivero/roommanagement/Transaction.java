package org.rivero.roommanagement;

import org.springframework.stereotype.Component;

import java.util.List;

public class Transaction {
    int moneyAmount;
    String receiverId;
    String senderId;
    String id;
    String description;
    public Transaction(int moneyAmount, String receiverId, String senderId, String id, String description) {
        this.moneyAmount = moneyAmount;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.id = id;
        this.description = description;
    }
}