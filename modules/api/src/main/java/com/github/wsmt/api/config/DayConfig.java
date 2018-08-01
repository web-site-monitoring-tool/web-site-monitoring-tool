package com.github.wsmt.api.config;

import com.github.wsmt.api.handler.DayStatisticsHandler;
import com.github.wsmt.api.repository.DayStatisticsRepository;
import com.github.wsmt.api.service.DayStatisticsService;
import com.github.wsmt.api.service.impl.RepositoryDayStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DayConfig {
    @Bean
    public RouterFunction<ServerResponse> dayStatisticsRouter(DayStatisticsHandler dayStatisticsHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/days"), dayStatisticsHandler);
    }

    @Bean
    public DayStatisticsHandler dayStatisticsHandler(DayStatisticsService dayStatisticsService) {
        return DayStatisticsHandler.byService(dayStatisticsService);
    }

    @Bean
    public DayStatisticsService dayStatisticsService(DayStatisticsRepository dayStatisticsRepository) {
        return RepositoryDayStatisticsService.byRepository(dayStatisticsRepository);
    }
}
