package com.github.wsmt.api.handler;

import com.github.wsmt.api.model.CountryStatistics;
import com.github.wsmt.api.service.CountryStatisticsService;
import org.junit.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CountryStatisticsHandlerTest {

    @Test
    public void byService() {
        CountryStatisticsService service = mock(CountryStatisticsService.class);
        CountryStatisticsHandler handler = CountryStatisticsHandler.byService(service);
        assertNotNull(handler);
    }

    @Test
    public void handle() {
        CountryStatisticsService service = mock(CountryStatisticsService.class);
        ServerRequest serverRequest = mock(ServerRequest.class);
        when(service.countryStatistics()).thenReturn(
                Flux.fromIterable(
                        new ArrayList<CountryStatistics>(
                                Arrays.asList(
                                        new CountryStatistics(),
                                        new CountryStatistics()
                                )
                        )
                )
        );
        CountryStatisticsHandler handler = CountryStatisticsHandler.byService(service);
        Mono<ServerResponse> responseMono = handler.handle(serverRequest);
        assertEquals(200, responseMono.block().statusCode().value());
    }
}