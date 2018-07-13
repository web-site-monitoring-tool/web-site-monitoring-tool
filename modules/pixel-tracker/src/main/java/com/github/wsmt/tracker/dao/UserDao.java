package com.github.wsmt.tracker.dao;

import com.github.wsmt.tracker.model.User;

public interface UserDao {
    User save(User user);
    void resetTable();
}
