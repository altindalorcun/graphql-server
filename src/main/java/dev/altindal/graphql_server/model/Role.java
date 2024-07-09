package dev.altindal.graphql_server.model;

import dev.altindal.graphql_server.exception.RoleNotFoundException;

public enum Role {
    ADMIN,
    USER;

    public static Role getRoleByName(String name) {
        return switch (name.toUpperCase()) {
            case "ADMIN" -> ADMIN;
            case "USER" -> USER;
            default -> throw new RoleNotFoundException();
        };
    }
}
