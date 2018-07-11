package com.github.wsmt.tracker.service;

import com.github.wsmt.tracker.dao.UserDao;
import com.github.wsmt.tracker.model.User;


public class UserService {

    private final String profileIdCookieName;
    private final UserDao userDao;

    private UserService(String profileIdCookieName, UserDao userDao) {
        this.profileIdCookieName = profileIdCookieName;
        this.userDao = userDao;
    }

    public static UserService create(String profileIdCookieName, UserDao userDao) {
        return new UserService(profileIdCookieName, userDao);
    }


    public User save(User user) {

        return userDao.save(user);
    }
}
