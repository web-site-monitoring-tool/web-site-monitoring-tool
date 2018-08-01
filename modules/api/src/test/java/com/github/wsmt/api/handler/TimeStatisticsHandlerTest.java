package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.TimeStatistics;
import com.github.wsmt.api.service.TimeStatisticsService;
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

public class TimeStatisticsHandlerTest {
    @Test
    public void byService() {
        TimeStatisticsService service = mock(TimeStatisticsService.class);
        TimeStatisticsHandler handler = TimeStatisticsHandler.byService(service);
        assertNotNull(handler);
    }

    @Test
    public void handle() {
        TimeStatisticsService service = mock(TimeStatisticsService.class);
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(service.timeStatistics()).thenReturn(
                Flux.fromIterable(
                        new ArrayList<TimeStatistics>(
                                Arrays.asList(
                                        new TimeStatistics(),
                                        new TimeStatistics()
                                )
                        )
                )
        );
        TimeStatisticsHandler handler = TimeStatisticsHandler.byService(service);
        Mono<ServerResponse> responseMono = handler.handle(serverRequest);
        assertEquals(200, responseMono.block().statusCode().value());
    }

}