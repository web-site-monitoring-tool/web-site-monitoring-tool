package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.PageStatistics;
import com.github.wsmt.api.repository.PageStatisticsRepository;
import com.github.wsmt.api.service.PageStatisticsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryPageStatisticsServiceTest {
    @Test
    public void byRepository() {
        PageStatisticsRepository repository = mock(PageStatisticsRepository.class);
        PageStatisticsService service = RepositoryPageStatisticsService.byRepository(repository);
        assertNotNull(service);
    }

    @Test
    public void browserStatistics() {
        PageStatisticsRepository repository = mock(PageStatisticsRepository.class);
        when(repository.findAll())
                .thenReturn(new ArrayList<PageStatistics>(
                        Arrays.asList(
                                new PageStatistics(),
                                new PageStatistics()
                        )
                ));
        PageStatisticsService service = RepositoryPageStatisticsService.byRepository(repository);
        assertTrue(service.pageStatistics().blockFirst() instanceof PageStatistics);
    }
}