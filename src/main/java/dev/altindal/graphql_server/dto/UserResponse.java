package dev.altindal.graphql_server.dto;

import dev.altindal.graphql_server.model.User;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        String username,
        String firstName,
        String lastName,
        String mail,
        String role
) {
    public UserResponse(User user) {
        this(user.getId(),user.getCreatedAt(), user
                .getUpdatedAt(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name());
    }
}
