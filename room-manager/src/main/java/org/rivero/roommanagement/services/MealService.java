package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.MealCheckListDto;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MealCheckListRepository;
import org.rivero.roommanagement.request.MealCreateRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealCheckListRepository mealCheckListRepository;

    public void insert(MealCreateRequest mealCheckList) {
        mealCheckListRepository.insert(new MealCheckListDto(UUID.randomUUID().toString(), mealCheckList.month(), mealCheckList.checklist(), mealCheckList.consumerid(), mealCheckList.time()));
    }

    public Collection<MealCheckListDto> getList() {
        return mealCheckListRepository.getList();
    }

    public void updateOne(MealCreateRequest mealCheckList, String id) {
        mealCheckListRepository.updateOne(new MealCheckListDto(id, mealCheckList.month(), mealCheckList.checklist(), mealCheckList.consumerid(), mealCheckList.time()));
    }

    public MealCheckListDto getOne(String id) {
        return mealCheckListRepository.getOne(id);
    }
}

