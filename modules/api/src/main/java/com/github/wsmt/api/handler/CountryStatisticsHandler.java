package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.CountryStatistics;
import com.github.wsmt.api.service.CountryStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class CountryStatisticsHandler implements HandlerFunction<ServerResponse> {

    private final CountryStatisticsService countryStatisticsService;

    private CountryStatisticsHandler(CountryStatisticsService countryStatisticsService) {
        this.countryStatisticsService = countryStatisticsService;
    }

    public static CountryStatisticsHandler byService(CountryStatisticsService statisticsService) {
        return new CountryStatisticsHandler(statisticsService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .body(
                        countryStatisticsService.countryStatistics(),
                        CountryStatistics.class
                );
    }
}
