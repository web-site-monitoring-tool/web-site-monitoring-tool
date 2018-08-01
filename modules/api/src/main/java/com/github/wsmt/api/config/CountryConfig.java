package com.github.wsmt.api.config;

import com.github.wsmt.api.handler.CountryStatisticsHandler;
import com.github.wsmt.api.repository.CountryStatisticsRepository;
import com.github.wsmt.api.service.CountryStatisticsService;
import com.github.wsmt.api.service.impl.RepositoryCountryStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CountryConfig {
    @Bean
    public RouterFunction<ServerResponse> countryStatisticsRouter(CountryStatisticsHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/countries"), handler);
    }

    @Bean
    public CountryStatisticsHandler byService(CountryStatisticsService service) {
        return CountryStatisticsHandler.byService(service);
    }

    @Bean
    public CountryStatisticsService countryStatisticsService(CountryStatisticsRepository repository) {
        return RepositoryCountryStatisticsService.byRepository(repository);
    }
}
