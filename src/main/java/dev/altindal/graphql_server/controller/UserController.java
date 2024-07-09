package dev.altindal.graphql_server.controller;

import dev.altindal.graphql_server.dto.UserCreateRequest;
import dev.altindal.graphql_server.dto.UserResponse;
import dev.altindal.graphql_server.dto.UserUpdateRequest;
import dev.altindal.graphql_server.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @QueryMapping
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }

    @QueryMapping
    public UserResponse getUserById(@Argument UUID id) {
        return service.getUserById(id);
    }

    @MutationMapping
    public UserResponse createUser(@Argument UserCreateRequest userCreateRequest) {
        return service.createUser(userCreateRequest);
    }

    @MutationMapping
    public UserResponse updateUser(@Argument UserUpdateRequest userUpdateRequest) {
        return service.updateUser(userUpdateRequest);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument UUID id) {
        service.deleteUser(id);
        return true;
    }

}
