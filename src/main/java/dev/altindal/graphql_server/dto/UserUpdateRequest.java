package dev.altindal.graphql_server.dto;

import java.util.UUID;

public record UserUpdateRequest(
        UUID id,
        String password,
        String firstName,
        String lastName,
        String mail,
        String role
) {
}
