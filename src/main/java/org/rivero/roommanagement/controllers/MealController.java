package org.rivero.roommanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.MealCheckListDTO;
import org.rivero.roommanagement.entities.Report;
import org.rivero.roommanagement.request.MealCreateRequest;
import org.rivero.roommanagement.services.MealService;
import org.rivero.roommanagement.services.ReceiptService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<List<MealCheckListDTO>> getList() {
        return ResponseEntity.ok().body(mealService.getList());
    }

    @GetMapping("/meals/{mealId}")
    public ResponseEntity<MealCheckListDTO> getOne(@PathVariable(name = "mealId") String mealId){
        return ResponseEntity.ok().body(mealService.getOne(mealId));
    }

    @PutMapping("/meals")
    public ResponseEntity<String>  insert(@RequestBody MealCreateRequest meal){
        mealService.insert(meal);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

}