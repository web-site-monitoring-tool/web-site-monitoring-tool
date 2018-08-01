package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.DayStatistics;
import com.github.wsmt.api.service.DayStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class DayStatisticsHandler implements HandlerFunction<ServerResponse> {
    private final DayStatisticsService dayStatisticsService;

    private DayStatisticsHandler(DayStatisticsService dayStatisticsService) {
        this.dayStatisticsService = dayStatisticsService;
    }

    public static DayStatisticsHandler byService(DayStatisticsService dayStatisticsService) {
        return new DayStatisticsHandler(dayStatisticsService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(
                        dayStatisticsService.dayStatistics(),
                        DayStatistics.class
                );
    }
}
