package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.dao.UserDao;
import com.github.wsmt.tracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserService userService(UserDao userDao) {
        return UserService.create(userDao);
    }
}
