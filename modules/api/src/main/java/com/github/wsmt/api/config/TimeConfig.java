package com.github.wsmt.api.config;

import com.github.wsmt.api.handler.TimeStatisticsHandler;
import com.github.wsmt.api.repository.TimeStatisticsRepository;
import com.github.wsmt.api.service.TimeStatisticsService;
import com.github.wsmt.api.service.impl.RepositoryTimeStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class TimeConfig {
    @Bean
    public RouterFunction<ServerResponse> timeStatisticsRouter(TimeStatisticsHandler timeStatisticsHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/times"), timeStatisticsHandler);
    }

    @Bean
    public TimeStatisticsHandler timeStatisticsHandler(TimeStatisticsService timeStatisticsService) {
        return TimeStatisticsHandler.byService(timeStatisticsService);
    }

    @Bean
    public TimeStatisticsService timeStatisticsService(TimeStatisticsRepository timeStatisticsRepository) {
        return RepositoryTimeStatisticsService.byRepository(timeStatisticsRepository);
    }

}
