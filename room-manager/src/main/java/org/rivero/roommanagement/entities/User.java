package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class User {
    String name;
    String id;
    String passwordHash;
    int balance;
    int debt;
    int role;

    public User(String id, String name, String passwordHash, int role, int balance, int debt) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
        this.balance = balance;
        this.debt = debt;
        this.id = id;
    }

    User getUserDetail() {
        return this;
    }

    void setUserName(String name) {
        this.name = name;
    }

    void setUserPassword(String passwordHash, String newPasswordHash) {
        if (this.passwordHash == passwordHash) this.passwordHash = newPasswordHash;
    }

    void increaseUserBalance(BigDecimal amount) {
//        this.balance = this.balance + amount;
    }
//    MoneyConsumeEvent createMoneyConsumeEvent(List<String> consumers){
//        int price = 1000;
//        MoneyConsumeEvent event = new MoneyConsumeEvent("Mua do an BHX", price, this.id, consumers, "mua com trung ca thit" );
//        this.balance = this.balance + price;
//        return event;
//    }
}