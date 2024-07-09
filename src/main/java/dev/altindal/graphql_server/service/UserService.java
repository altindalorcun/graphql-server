package dev.altindal.graphql_server.service;

import dev.altindal.graphql_server.dto.UserCreateRequest;
import dev.altindal.graphql_server.dto.UserResponse;
import dev.altindal.graphql_server.dto.UserUpdateRequest;
import dev.altindal.graphql_server.exception.UserNotFoundException;
import dev.altindal.graphql_server.model.Role;
import dev.altindal.graphql_server.model.User;
import dev.altindal.graphql_server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        return repository.findById(id).map(UserResponse::new).orElseThrow(UserNotFoundException::new);
    }

    public UserResponse createUser(UserCreateRequest request) {
        User createdUser = repository.save(new User(request));
        return new UserResponse(createdUser);
    }

    public UserResponse updateUser(UserUpdateRequest request) {
        User oldUser = repository.findById(request.id()).orElseThrow(UserNotFoundException::new);
        oldUser.setUsername(request.username());
        oldUser.setPassword(request.password());
        oldUser.setFirstName(request.firstName());
        oldUser.setLastName(request.lastName());
        oldUser.setEmail(request.mail());
        oldUser.setRole(request.role());
        User updated = repository.save(oldUser);
        return new UserResponse(updated);
    }

    public void deleteUser(UUID id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.delete(user);
    }

}
