package org.rivero.roommanager.subscription;

import java.time.ZonedDateTime;

public record MealCheckListDto(
        String id,
        String month,
        String checkList,
        String consumerId,
        ZonedDateTime time
) {
}
