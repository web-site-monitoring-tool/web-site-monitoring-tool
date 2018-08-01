package com.github.wsmt.reporter.config;

import com.github.wsmt.reporter.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public com.github.wsmt.reporter.ApplicationContext applicationContext(
            org.apache.hadoop.conf.Configuration hBaseConfiguration) {

        return ApplicationContext.create(hBaseConfiguration, null);
    }
}
