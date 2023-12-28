package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MoneyConsumeEvent {
    String name;
    int moneyAmount;
    String buyerId;
    List<String> consumerList = new ArrayList<>();
    String id;
    String description;
    ZonedDateTime createddate;

    public MoneyConsumeEvent(String id, String name,int moneyAmount, String buyerId, List<String> consumerList, String description, ZonedDateTime createddate) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumerList = consumerList;
        this.description = description;
        this.createddate = createddate;
    }
    public MoneyConsumeEvent(String id, String name,int moneyAmount, String buyerId, String consumerList, String description) {
        this.id = id;
        this.name = name;
        this.moneyAmount = moneyAmount;
        this.buyerId = buyerId;
        this.consumerList.add(consumerList);
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