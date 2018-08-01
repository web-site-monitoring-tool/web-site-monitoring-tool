package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.TimeStatistics;
import com.github.wsmt.api.service.TimeStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class TimeStatisticsHandler implements HandlerFunction<ServerResponse> {
    private final TimeStatisticsService timeStatisticsService;

    private TimeStatisticsHandler(TimeStatisticsService timeStatisticsService) {
        this.timeStatisticsService = timeStatisticsService;
    }

    public static TimeStatisticsHandler byService(TimeStatisticsService timeStatisticsService) {
        return new TimeStatisticsHandler(timeStatisticsService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(
                        timeStatisticsService.timeStatistics(),
                        TimeStatistics.class
                );
    }
}
