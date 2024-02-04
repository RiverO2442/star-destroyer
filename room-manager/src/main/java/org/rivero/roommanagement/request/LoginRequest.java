package org.rivero.roommanagement.request;

public record LoginRequest(
        String username,
        String password
) {
}
