package org.rivero.roommanager.receipt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMoneyConsumeRequestRightWay {
    List<String> consumerIds = new ArrayList<>();
}
