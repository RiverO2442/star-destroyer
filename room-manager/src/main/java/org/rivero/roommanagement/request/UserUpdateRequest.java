package org.rivero.roommanagement.request;

public record UserUpdateRequest(
        String passwordHash,
        String id
) {
}
