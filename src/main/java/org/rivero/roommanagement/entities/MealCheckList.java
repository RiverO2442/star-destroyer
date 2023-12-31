package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Array;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class MealCheckList {
    String id;
    String month;
    Array checkList;
    String consumerId;
    ZonedDateTime createDate;
    public MealCheckList(String id, String month, Array checkList, String consumerId, ZonedDateTime createDate){
        this.id = id;
        this.month = month;
        this.checkList = checkList;
        this.consumerId = consumerId;
        this.createDate = createDate;
    }
}
