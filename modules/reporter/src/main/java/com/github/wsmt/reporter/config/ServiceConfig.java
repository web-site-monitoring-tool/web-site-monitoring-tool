package com.github.wsmt.reporter.config;

import com.github.wsmt.reporter.ApplicationContext;
import com.github.wsmt.reporter.service.ReportService;
import com.github.wsmt.reporter.service.impl.ReportServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {
    @Bean
    public ReportService reportService(ApplicationContext applicationContext) {
        return ReportServiceImpl.create(applicationContext);
    }
}
