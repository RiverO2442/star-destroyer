package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.MealCheckListDto;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MealCheckListRepository;
import org.rivero.roommanagement.request.MealCreateRequest;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealCheckListRepository mealCheckListRepository;
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    public void insert(MealCreateRequest mealCheckList) {
        mealCheckListRepository.insert(connection, new MealCheckListDto(UUID.randomUUID().toString(), mealCheckList.month(), mealCheckList.checklist(), mealCheckList.consumerid(), mealCheckList.time()));
    }

    public List<MealCheckListDto> getList() {
        return mealCheckListRepository.getList(connection);
    }

    public void updateOne(MealCreateRequest mealCheckList, String id) {
        mealCheckListRepository.updateOne(connection, new MealCheckListDto(id, mealCheckList.month(), mealCheckList.checklist(), mealCheckList.consumerid(), mealCheckList.time()));
    }

    public MealCheckListDto getOne(String id) {
        return mealCheckListRepository.getOne(connection, id);
    }
}

