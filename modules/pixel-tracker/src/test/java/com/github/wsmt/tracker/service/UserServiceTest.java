package com.github.wsmt.tracker.service;

import com.github.wsmt.tracker.dao.UserDao;
import com.github.wsmt.tracker.model.User;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void create() {
        UserDao userDao = mock(UserDao.class);
        UserService userService = UserService.create(userDao);
        assertNotNull(userService);
    }

    @Test
    public void save() {
        UserDao userDao = mock(UserDao.class);
        User user = new User();
        when(userDao.save(user)).thenReturn(user);

        UserService userService = UserService.create(userDao);
        User fromDb = userService.save(user);
        assertEquals(user, fromDb);
    }
}