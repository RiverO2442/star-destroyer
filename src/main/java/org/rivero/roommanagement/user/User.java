package org.rivero.roommanagement.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class User {
    String id;
    String name;
    int balance;

    User(String name, String passwordHash, int role) {
        this.name = name;
        this.balance = 0;
        this.id = UUID.randomUUID().toString();
    }

    User getUserDetail() {
        return this;
    }

    void setUserName(String name) {
        this.name = name;
    }

    void increaseUserBalance(int amount) {
        this.balance = this.balance + amount;
    }

}