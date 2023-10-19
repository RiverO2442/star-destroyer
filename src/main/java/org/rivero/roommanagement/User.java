package org.rivero.roommanagement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "haodz")
@NoArgsConstructor
public class User{
    @Id
    String id;
    String name;
    String passwordHash;
    int balance;
    int role;

    User(String name, String passwordHash, int role){
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
        this.balance = 0;
        this.id = UUID.randomUUID().toString();
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

    MoneyConsumeEvent createMoneyConsumeEvent(List<String> consumerList){
        int price = 1000;
        MoneyConsumeEvent event = new MoneyConsumeEvent("Mua do an BHX", price, this.id, consumerList, "mua com trung ca thit" );
        this.balance = this.balance + price;
        return event;
    }
}