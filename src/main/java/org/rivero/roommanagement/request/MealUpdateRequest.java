package org.rivero.roommanagement.request;

import java.time.ZonedDateTime;

public record MealUpdateRequest(
        String month,
        String consumerId,
        String checkList,
        ZonedDateTime time

) {
}
