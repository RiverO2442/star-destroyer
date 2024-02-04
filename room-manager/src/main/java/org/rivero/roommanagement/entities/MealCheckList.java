package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class MealCheckList {
    String id;
    String month;
    String checkList;
    String consumerId;
    ZonedDateTime createDate;

    public MealCheckList(String id, String month, String checkList, String consumerId, ZonedDateTime createDate) {
        this.id = id;
        this.month = month;
        this.checkList = checkList;
        this.consumerId = consumerId;
        this.createDate = createDate;
    }
}