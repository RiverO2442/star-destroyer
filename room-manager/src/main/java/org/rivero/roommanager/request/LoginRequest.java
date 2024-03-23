package org.rivero.roommanager.request;

public record LoginRequest(
        String username,
        String password
) {
}
