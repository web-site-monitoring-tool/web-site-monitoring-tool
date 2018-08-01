package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.PageStatistics;
import com.github.wsmt.api.service.PageStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PageStatisticsHandler implements HandlerFunction<ServerResponse> {
    private final PageStatisticsService pageStatisticsService;

    private PageStatisticsHandler(PageStatisticsService pageStatisticsService) {
        this.pageStatisticsService = pageStatisticsService;
    }

    public static PageStatisticsHandler byService(PageStatisticsService pageStatisticsService) {
        return new PageStatisticsHandler(pageStatisticsService);
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.ok()
                .body(
                        pageStatisticsService.pageStatistics(),
                        PageStatistics.class
                );
    }
}
