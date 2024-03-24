package org.rivero.roommanager.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

