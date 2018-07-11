package com.github.wsmt.tracker.service;

public class UserService {

    private final String profileIdCookieName;

    private UserService(String profileIdCookieName) {
        this.profileIdCookieName = profileIdCookieName;
    }

    public static UserService create(String profileIdCookieName) {
        return new UserService(profileIdCookieName);
    }


    public void parse() {

    }
}
