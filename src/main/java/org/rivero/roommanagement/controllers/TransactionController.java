package org.rivero.roommanagement.controllers;

import org.rivero.roommanagement.dtos.CreateMoneyConsumeRequestRightWay;
import org.rivero.roommanagement.dtos.CreateMoneyConsumeRequestWrongWay;
import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final MoneyConsumeEventRepository repository;
    public TransactionController(MoneyConsumeEventRepository repository){
        this.repository = repository;
    }

    @PostMapping("/user")
    public ResponseEntity<MoneyConsumeEvent> addUser(@RequestBody CreateMoneyConsumeRequestRightWay event) {
        // luu create money consume event xuong database
        MoneyConsumeEvent entity = new MoneyConsumeEvent("", 0, "", event.getConsumerIds(), "description");
        repository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PostMapping("/bad-case")
    public ResponseEntity<Void> addUserBadCase(@RequestBody CreateMoneyConsumeRequestWrongWay body) {
        // tim user co ten tuong ung
        String prepareStatement = """
                select id from user u where u.name in :customerNames
                """;
        prepareStatement.set("customerNames", body.getConsumerNames());
        List<String> consumerIds = body.getConsumerNames();
        // luu create money consume event xuong database
        repository.save(new MoneyConsumeEvent("", 0, "", consumerIds, "description"));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
