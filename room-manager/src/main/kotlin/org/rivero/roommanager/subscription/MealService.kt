package org.rivero.roommanager.subscription

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class MealService {
    private val mealCheckListRepository: MealCheckListRepository? = null

    fun insert(mealCheckList: MealCreateRequest) {
        mealCheckListRepository!!.insert(
            MealCheckListDto(
                UUID.randomUUID().toString(),
                mealCheckList.month,
                mealCheckList.checklist,
                mealCheckList.consumerId,
                mealCheckList.time
            )
        )
    }

    val list: Collection<MealCheckListDto>
        get() = mealCheckListRepository!!.list

    fun updateOne(mealCheckList: MealCreateRequest, id: String) {
        mealCheckListRepository!!.updateOne(
            MealCheckListDto(
                id,
                mealCheckList.month,
                mealCheckList.checklist,
                mealCheckList.consumerId,
                mealCheckList.time
            )
        )
    }

    fun getOne(id: String): MealCheckListDto? {
        return mealCheckListRepository!!.getOne(id)
    }
}

