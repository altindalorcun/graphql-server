package dev.altindal.graphql_server.model;

import dev.altindal.graphql_server.dto.UserCreateRequest;
import dev.altindal.graphql_server.dto.UserUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {

    @Column(columnDefinition = "character varying(100)", name = "username", unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "character varying(200)", name = "password", nullable = false)
    private String password;

    @Column(columnDefinition = "character varying(100)", name = "first_name", nullable = false)
    private String firstName;

    @Column(columnDefinition = "character varying(100)", name = "last_name", nullable = false)
    private String lastName;

    @Column(columnDefinition = "character varying(100)", name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserCreateRequest request) {
        setUsername(request.username());
        setPassword(request.password());
        setFirstName(request.firstName());
        setLastName(request.lastName());
        setEmail(request.mail());
        setRole(request.role());
    }

    public User(UserUpdateRequest request) {
        setUsername(request.username());
        setPassword(request.password());
        setFirstName(request.firstName());
        setLastName(request.lastName());
        setEmail(request.mail());
        setRole(request.role());
    }
}
