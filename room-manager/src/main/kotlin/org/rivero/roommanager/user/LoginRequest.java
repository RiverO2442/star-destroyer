package org.rivero.roommanager.user;

public record LoginRequest(
        String username,
        String password
) {
}
