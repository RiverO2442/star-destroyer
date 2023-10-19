package org.rivero.roommanagement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class MoneyConsumeEvent {
    @Id
    String id;
    String name;
    int moneyAmount;
    String buyerId;
    List<String> consumerList;
    String description;

    public MoneyConsumeEvent(String name, int moneyAmount, String buyerId, List<String> consumerList, String description) {
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumerList = consumerList;
        this.id = UUID.randomUUID().toString();
        this.description = description;
    }
    void accounting(List<User> userList){
        userList.forEach(user -> {
            if(this.consumerList.contains(user.getName())){
                user.increaseUserBalance(-1 * this.moneyAmount / this.consumerList.size());
            }
        });
    }
}