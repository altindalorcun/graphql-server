package dev.altindal.graphql_server.dto;

import dev.altindal.graphql_server.model.Role;

import java.util.UUID;

public record UserUpdateRequest(
        UUID id,
        String username,
        String password,
        String firstName,
        String lastName,
        String mail,
        Role role
) {
}
