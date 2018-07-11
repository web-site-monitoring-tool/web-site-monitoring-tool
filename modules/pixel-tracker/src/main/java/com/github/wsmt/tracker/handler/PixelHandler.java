package com.github.wsmt.tracker.handler;

import com.github.wsmt.tracker.model.User;
import com.github.wsmt.tracker.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpCookie;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.ResponseCookie;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


public class PixelHandler implements HandlerFunction<ServerResponse> {

    private final BodyInserter<Resource, ReactiveHttpOutputMessage> responseBody;
    private final String profileIdCookieName;
    private final UserService userService;

    private PixelHandler(BodyInserter<Resource, ReactiveHttpOutputMessage> responseBody,
                         String profileIdCookieName, UserService userService) {
        this.responseBody = responseBody;
        this.profileIdCookieName = profileIdCookieName;
        this.userService = userService;
    }

    public static PixelHandler newPixelHandler(Resource pixel, String profileIdCookieName, UserService userService) {
        return new PixelHandler(
                BodyInserters.fromResource(pixel),
                profileIdCookieName,
                userService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        User user = getUser(serverRequest);

        user.setBehavior(serverRequest.headers().asHttpHeaders().toSingleValueMap());
        userService.save(user);

        if (user.isNewProfile()) {
            return ServerResponse.ok()
                    .cookie(ResponseCookie.from(profileIdCookieName, user.getId())
                            .build())
                    .body(responseBody);
        }
        return ServerResponse.ok()
                .body(responseBody);
    }

    private User getUser(ServerRequest serverRequest) {

        HttpCookie userIdCookies = serverRequest.cookies()
                .getFirst(profileIdCookieName);
        boolean newProfile = userIdCookies == null;
        User user;
        if (newProfile) {
            user = new User();
        } else {
            user = new User(userIdCookies.getValue());
        }
        return user;
    }
}
