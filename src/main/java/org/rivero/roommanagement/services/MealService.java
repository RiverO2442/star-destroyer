package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.dtos.MealCheckListDTO;
import org.rivero.roommanagement.entities.MealCheckList;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MealCheckListRepository;
import org.rivero.roommanagement.request.MealCreateRequest;
import org.rivero.roommanagement.request.MealUpdateRequest;
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
    public void insert(MealCreateRequest mealCheckList){
        mealCheckListRepository.insert(connection, new MealCheckListDTO(UUID.randomUUID().toString(), mealCheckList.month(), mealCheckList.checkList(), mealCheckList.consumerId(), mealCheckList.time()));
    }

    public List<MealCheckListDTO> getList(){
        return mealCheckListRepository.getList(connection);
    }

    public void updateOne(MealUpdateRequest mealCheckList, String id){
        mealCheckListRepository.updateOne(connection, new MealCheckListDTO(id, mealCheckList.month(), mealCheckList.checkList(), mealCheckList.consumerId(), mealCheckList.time()));
    }

    public MealCheckListDTO getOne(String id){
        return mealCheckListRepository.getOne(connection, id);
    }

    public void deleteOne(String id){
        mealCheckListRepository.deleteOne(connection, id);
    }
}

