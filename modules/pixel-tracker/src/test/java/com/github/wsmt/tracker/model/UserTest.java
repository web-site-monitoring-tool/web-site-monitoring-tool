package com.github.wsmt.tracker.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testIdCreation() {
        User user = new User();
        assertTrue(user.getId().length() > 0);
    }

    @Test
    public void testCreationUserWithId() {
        User user = new User("123456");
        assertEquals("123456", user.getId());
    }

    @Test
    public void isNewProfile() {
        User user = new User();
        assertTrue(user.isNewProfile());
    }

    @Test
    public void isNotNewProfile() {
        User user = new User("123456");
        assertFalse(user.isNewProfile());
    }
}