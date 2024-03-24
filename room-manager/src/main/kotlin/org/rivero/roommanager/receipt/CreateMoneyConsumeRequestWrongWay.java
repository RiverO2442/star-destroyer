package org.rivero.roommanager.receipt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMoneyConsumeRequestWrongWay {
    List<String> consumerNames = new ArrayList<>();
}
