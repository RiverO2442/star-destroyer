package org.rivero.roommanagement.request;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

public record MealCreateRequest(
        String month,
        String consumerid,
        String checklist,
        ZonedDateTime time

) {
}
