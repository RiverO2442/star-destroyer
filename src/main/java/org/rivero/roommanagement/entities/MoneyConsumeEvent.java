package org.rivero.roommanagement.entities;

import java.util.List;
import java.util.UUID;

public class MoneyConsumeEvent {
    String name;
    int moneyAmount;
    String buyerId;
    List<String> consumerList;
    String id;
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
            if(this.consumerList.contains(user.getId())){
                user.increaseUserBalance(-1 * this.moneyAmount / this.consumerList.size());
            }
        });
    }
}