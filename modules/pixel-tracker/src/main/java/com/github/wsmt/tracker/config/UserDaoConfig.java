package com.github.wsmt.tracker.config;

import com.github.wsmt.tracker.dao.UserDao;
import com.github.wsmt.tracker.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoConfig {
    @Bean
    UserDao userDao(org.apache.hadoop.conf.Configuration hBaseConfiguration,
                    @Value("${profile.table}") String profileTable) {
        return UserDaoImpl.newUserDaoImpl(hBaseConfiguration, profileTable);
    }
}
