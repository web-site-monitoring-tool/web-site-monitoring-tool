package com.github.wsmt.api.config;

import com.github.wsmt.api.handler.PageStatisticsHandler;
import com.github.wsmt.api.repository.PageStatisticsRepository;
import com.github.wsmt.api.service.PageStatisticsService;
import com.github.wsmt.api.service.impl.RepositoryPageStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PageConfig {
    @Bean
    public RouterFunction<ServerResponse> pageStatisticsRouter(PageStatisticsHandler pageStatisticsHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/pages"), pageStatisticsHandler);
    }

    @Bean
    public PageStatisticsHandler pageStatisticsHandler(PageStatisticsService pageStatisticsService) {
        return PageStatisticsHandler.byService(pageStatisticsService);
    }

    @Bean
    public PageStatisticsService pageStatisticsService(PageStatisticsRepository pageStatisticsRepository) {
        return RepositoryPageStatisticsService.byRepository(pageStatisticsRepository);
    }
}
