package org.rivero.roommanagement.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMoneyConsumeRequestWrongWay {
    List<String> consumerNames = new ArrayList<>();
}
