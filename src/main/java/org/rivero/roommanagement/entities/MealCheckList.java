package org.rivero.roommanagement.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Array;
import java.util.ArrayList;

@Getter
@Setter
public class MealCheckList {
    String id;
    String month;
    Array checkList;
    String consumerId;
    public MealCheckList(String id, String month, Array checkList, String consumerId){
        this.id = id;
        this.month = month;
        this.checkList = checkList;
        this.consumerId = consumerId;
    }
}
