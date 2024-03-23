package org.rivero.roommanager.request;

import java.time.ZonedDateTime;

public record MealCreateRequest(
        String month,
        String consumerid,
        String checklist,
        ZonedDateTime time

) {
}
