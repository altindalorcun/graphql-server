package dev.altindal.graphql_server.dto;

import dev.altindal.graphql_server.model.Role;

public record UserCreateRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String mail,
        Role role
) {
}
