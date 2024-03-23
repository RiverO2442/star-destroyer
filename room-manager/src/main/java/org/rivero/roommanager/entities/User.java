package org.rivero.roommanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    String id;
    String name;
    BigDecimal balance = BigDecimal.ZERO;
    int debt;
    int role;


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