package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.PageStatistics;
import com.github.wsmt.api.service.PageStatisticsService;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PageStatisticsHandler implements HandlerFunction<ServerResponse> {
    private final Mono<ServerResponse> serverResponse;

    private PageStatisticsHandler(Mono<ServerResponse> serverResponse) {
        this.serverResponse = serverResponse;
    }

    public static PageStatisticsHandler byPageService(PageStatisticsService pageStatisticsService) {
        return new PageStatisticsHandler(
                ServerResponse.ok()
                        .body(
                                pageStatisticsService.pageStatistics(),
                                PageStatistics.class
                        )
        );
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return serverResponse;
    }
}
