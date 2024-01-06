package org.rivero.roommanagement.request;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

public record MealCreateRequest(
        String month,
        String consumerId,
        String checkList,
        ZonedDateTime time

) {
}
