package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserService userService(@Value("${profile.id.cookie-name}") String profileIdCookieName) {
        return UserService.create(profileIdCookieName);
    }
}
