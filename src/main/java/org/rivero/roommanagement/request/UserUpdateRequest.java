package org.rivero.roommanagement.request;

import java.time.ZonedDateTime;

public record UserUpdateRequest(
        String passwordHash,
        String id
) {
}
