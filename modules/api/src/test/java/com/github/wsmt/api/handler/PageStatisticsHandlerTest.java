package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.PageStatistics;
import com.github.wsmt.api.service.PageStatisticsService;
import org.junit.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageStatisticsHandlerTest {

    @Test
    public void byService() {
        PageStatisticsService service = mock(PageStatisticsService.class);
        PageStatisticsHandler handler = PageStatisticsHandler.byService(service);
        assertNotNull(handler);
    }

    @Test
    public void handle() {
        PageStatisticsService service = mock(PageStatisticsService.class);
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(service.pageStatistics()).thenReturn(
                Flux.fromIterable(
                        new ArrayList<PageStatistics>(
                                Arrays.asList(
                                        new PageStatistics(),
                                        new PageStatistics()
                                )
                        )
                )
        );
        PageStatisticsHandler handler = PageStatisticsHandler.byService(service);
        Mono<ServerResponse> responseMono = handler.handle(serverRequest);
        assertEquals(200, responseMono.block().statusCode().value());
    }
}