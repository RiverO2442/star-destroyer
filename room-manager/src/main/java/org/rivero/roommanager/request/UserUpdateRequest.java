package org.rivero.roommanager.request;

public record UserUpdateRequest(
        String passwordHash,
        String id
) {
}
