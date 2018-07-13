package com.github.wsmt.tracker.handler;

import com.github.wsmt.tracker.service.UserService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class DBHandler implements HandlerFunction<ServerResponse> {

    private final UserService userService;

    public DBHandler(UserService userService) {
        this.userService = userService;
    }

    public static DBHandler create(UserService userService) {
        return new DBHandler(userService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        userService.resetProfileTable();
        return ServerResponse.ok()
                .build();
    }
}
