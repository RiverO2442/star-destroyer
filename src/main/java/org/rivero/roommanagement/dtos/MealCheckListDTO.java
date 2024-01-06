package org.rivero.roommanagement.dtos;

import java.sql.Array;
import java.time.ZonedDateTime;
import java.util.List;

public record MealCheckListDTO(
        String id,
        String month,
        String checkList,
        String consumerId,
        ZonedDateTime time
) {
}
