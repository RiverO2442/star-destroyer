package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class User{
    String name;
    String id;
    String passwordHash;
    int balance;
    int role;
    public User(String id, String name, String passwordHash, int role){
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
        this.balance = 0;
        this.id = id;
    }
    User getUserDetail(){
        return this;
    }
    void setUserName(String name){
        this.name = name;
    }
    void setUserPassword(String passwordHash, String newPasswordHash){
        if(this.passwordHash == passwordHash) this.passwordHash = newPasswordHash;
    }
    void increaseUserBalance(int amount){
        this.balance = this.balance + amount;
    }
//    MoneyConsumeEvent createMoneyConsumeEvent(List<String> consumerList){
//        int price = 1000;
//        MoneyConsumeEvent event = new MoneyConsumeEvent("Mua do an BHX", price, this.id, consumerList, "mua com trung ca thit" );
//        this.balance = this.balance + price;
//        return event;
//    }
}