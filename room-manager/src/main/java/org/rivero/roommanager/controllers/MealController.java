package org.rivero.roommanager.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanager.dtos.MealCheckListDto;
import org.rivero.roommanager.request.MealCreateRequest;
import org.rivero.roommanager.services.MealService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<Collection<MealCheckListDto>> getList() {
        return ResponseEntity.ok().body(mealService.getList());
    }

    @GetMapping("/meals/{mealId}")
    public ResponseEntity<MealCheckListDto> getOne(@PathVariable(name = "mealId") String mealId) {
        return ResponseEntity.ok().body(mealService.getOne(mealId));
    }

    @PutMapping("/meals")
    public ResponseEntity<String> insert(@RequestBody MealCreateRequest meal) {
        mealService.insert(meal);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

}