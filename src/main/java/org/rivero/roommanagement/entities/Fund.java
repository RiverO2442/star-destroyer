package org.rivero.roommanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Fund {
    int moneyAmount;
    List<Transaction> transactionList;
}