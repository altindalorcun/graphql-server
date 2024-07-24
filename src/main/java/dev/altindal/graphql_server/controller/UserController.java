package dev.altindal.graphql_server.controller;

import dev.altindal.graphql_server.dto.UserCreateRequest;
import dev.altindal.graphql_server.dto.UserResponse;
import dev.altindal.graphql_server.dto.UserUpdateRequest;
import dev.altindal.graphql_server.service.UserService;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService service;
    private FluxSink<UserResponse> userStream;
    private final ConnectableFlux<UserResponse> userPublisher;

    public UserController(UserService service) {
        this.service = service;
        Flux<UserResponse> publisher = Flux.create(emitter -> {
            userStream = emitter;
        });
        userPublisher = publisher.publish();
        userPublisher.connect();
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
        UserResponse userResponse = service.createUser(userCreateRequest);
        userStream.next(userResponse);
        return userResponse;
    }

    @MutationMapping
    public UserResponse updateUser(@Argument UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = service.updateUser(userUpdateRequest);
        userStream.next(userResponse);
        return userResponse;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument UUID id) {
        service.deleteUser(id);
        return true;
    }

    @SubscriptionMapping
    public Publisher<UserResponse> userStatusChanged() {
        return userPublisher;
    }

}
