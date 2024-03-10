package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MoneyConsumeEvent {
    String name;
    int moneyAmount;
    String buyerId;
    List<String> consumers = new ArrayList<>();
    String id;
    String description;
    ZonedDateTime createdDate;

    public MoneyConsumeEvent(String id, String name, int moneyAmount, String buyerId, List<String> consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumers = consumerList;
        this.description = description;
        this.createdDate = createddate;
    }

    public MoneyConsumeEvent(String id, String name, int moneyAmount, String buyerId, String consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumers.add(consumerList);
        this.description = description;
        this.createdDate = createddate;
    }

    void accounting(List<User> userList) {
        userList.forEach(user -> {
            if (this.consumers.contains(user.getId())) {
                user.increaseUserBalance(-1 * this.moneyAmount / this.consumers.size());
            }
        });
    }
}