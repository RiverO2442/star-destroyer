package org.rivero.roommanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Fund {
    int moneyAmount;
    List<Transaction> transactionList;
}