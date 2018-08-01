package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.BrowserStatistics;
import com.github.wsmt.api.repository.BrowserStatisticsRepository;
import com.github.wsmt.api.service.BrowserStatisticsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RepositoryBrowserStatisticsServiceTest {

    @Test
    public void byRepository() {
        BrowserStatisticsRepository repository = mock(BrowserStatisticsRepository.class);
        BrowserStatisticsService service = RepositoryBrowserStatisticsService.byRepository(repository);
        assertNotNull(service);
    }

    @Test
    public void browserStatistics() {
        BrowserStatisticsRepository repository = mock(BrowserStatisticsRepository.class);
        when(repository.findAll())
                .thenReturn(new ArrayList<BrowserStatistics>(
                        Arrays.asList(
                                new BrowserStatistics(),
                                new BrowserStatistics()
                        )
                ));
        BrowserStatisticsService service = RepositoryBrowserStatisticsService.byRepository(repository);
        assertTrue(service.browserStatistics().blockFirst() instanceof BrowserStatistics);
    }
}