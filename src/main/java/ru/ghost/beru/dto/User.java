package ru.ghost.beru.dto;

public record User(
        String username,
        String password,
        String roles
) {
}
