package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.service.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestServiceConfig {

    @Bean
    public RequestService saveService(@Value("${profile.id.cookie-name}") String profileIdCookieName) {
        return RequestService.create(profileIdCookieName);
    }
}
