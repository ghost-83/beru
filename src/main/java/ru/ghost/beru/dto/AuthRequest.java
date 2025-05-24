package ru.ghost.beru.dto;

public record AuthRequest(
        String username,
        String password
) {
}
