package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.BrowserStatistics;
import com.github.wsmt.api.service.BrowserStatisticsService;
import org.junit.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class BrowserStatisticsHandlerTest {

    @Test
    public void byService() {
        BrowserStatisticsService service = mock(BrowserStatisticsService.class);
        when(service.browserStatistics())
                .thenReturn(
                        Flux.fromIterable(
                                new ArrayList<>()
                        )
                );
        BrowserStatisticsHandler handler = BrowserStatisticsHandler.byService(service);
        assertNotNull(handler);
    }

    @Test
    public void handle() {
        BrowserStatisticsService service = mock(BrowserStatisticsService.class);
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(service.browserStatistics())
                .thenReturn(
                        Flux.fromIterable(
                                new ArrayList<BrowserStatistics>(
                                        Arrays.asList(
                                                new BrowserStatistics(),
                                                new BrowserStatistics()
                                        )
                                )
                        )
                );
        BrowserStatisticsHandler handler = BrowserStatisticsHandler.byService(service);
        Mono<ServerResponse> responseMono = handler.handle(serverRequest);
        assertEquals(200, responseMono.block().statusCode().value());
    }
}