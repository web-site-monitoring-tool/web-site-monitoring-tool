package com.github.wsmt.tracker.service;

import com.github.wsmt.tracker.dao.UserDao;
import com.github.wsmt.tracker.model.User;


public class UserService {

    private final UserDao userDao;

    private UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserService create(UserDao userDao) {
        return new UserService(userDao);
    }


    public User save(User user) {

        return userDao.save(user);
    }
}
