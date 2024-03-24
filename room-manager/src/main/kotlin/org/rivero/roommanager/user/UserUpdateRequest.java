package org.rivero.roommanager.user;

public record UserUpdateRequest(
        String passwordHash,
        String id
) {
}
