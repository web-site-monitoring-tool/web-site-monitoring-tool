package com.github.wsmt.tracker.service;

import com.github.wsmt.tracker.model.User;
import org.springframework.http.HttpCookie;
import org.springframework.web.reactive.function.server.ServerRequest;
import java.util.List;

public class RequestService {

    private final String profileIdCookieName;

    private RequestService(String profileIdCookieName) {
        this.profileIdCookieName = profileIdCookieName;
    }

    public static RequestService create(String profileIdCookieName) {
        return new RequestService(profileIdCookieName);
    }


    public void parse(ServerRequest serverRequest) {

        List<HttpCookie> userIdCookies = serverRequest.cookies()
                .get(profileIdCookieName);
        boolean newProfile = userIdCookies == null || userIdCookies.isEmpty();
        User user;
        if (newProfile) {
            user = new User();
        } else {
            user = new User(
                    userIdCookies.stream()
                    .reduce((firstCookie, lastCookie) -> lastCookie)
                    .map(HttpCookie::getValue)
                    .get()
            );
        }
        user.setBehavior(serverRequest.headers()
                .asHttpHeaders()
                .toSingleValueMap()
        );
    //TODO: save user into database
    }
}
