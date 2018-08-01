package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.DayStatistics;
import com.github.wsmt.api.service.DayStatisticsService;
import org.junit.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DayStatisticsHandlerTest {

    @Test
    public void byService() {
        DayStatisticsService service = mock(DayStatisticsService.class);
        DayStatisticsHandler handler = DayStatisticsHandler.byService(service);
        assertNotNull(handler);
    }

    @Test
    public void handle() {
        DayStatisticsService service = mock(DayStatisticsService.class);
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(service.dayStatistics()).thenReturn(
                Flux.fromIterable(
                        new ArrayList<DayStatistics>(
                                Arrays.asList(
                                        new DayStatistics(),
                                        new DayStatistics()
                                )
                        )
                )
        );
        DayStatisticsHandler handler = DayStatisticsHandler.byService(service);
        Mono<ServerResponse> responseMono = handler.handle(serverRequest);
        assertEquals(200, responseMono.block().statusCode().value());
    }
}