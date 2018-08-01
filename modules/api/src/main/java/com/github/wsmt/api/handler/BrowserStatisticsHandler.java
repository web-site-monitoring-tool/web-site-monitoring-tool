package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.BrowserStatistics;
import com.github.wsmt.api.service.BrowserStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class BrowserStatisticsHandler implements HandlerFunction<ServerResponse> {
    private final BrowserStatisticsService browserStatisticsService;

    private BrowserStatisticsHandler(BrowserStatisticsService browserStatisticsService) {
        this.browserStatisticsService = browserStatisticsService;
    }

    public static BrowserStatisticsHandler byService(BrowserStatisticsService browserStatisticsService) {
        return new BrowserStatisticsHandler(browserStatisticsService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.ok()
                .body(
                        browserStatisticsService.browserStatistics(),
                        BrowserStatistics.class
                );
    }
}
