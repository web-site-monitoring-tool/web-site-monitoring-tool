package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.DayStatistics;
import com.github.wsmt.api.repository.DayStatisticsRepository;
import com.github.wsmt.api.service.DayStatisticsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryDayStatisticsServiceTest {
    @Test
    public void byRepository() {
        DayStatisticsRepository repository = mock(DayStatisticsRepository.class);
        DayStatisticsService service = RepositoryDayStatisticsService.byRepository(repository);
        assertNotNull(service);
    }

    @Test
    public void browserStatistics() {
        DayStatisticsRepository repository = mock(DayStatisticsRepository.class);
        when(repository.findAll())
                .thenReturn(new ArrayList<DayStatistics>(
                        Arrays.asList(
                                new DayStatistics(),
                                new DayStatistics()
                        )
                ));
        DayStatisticsService service = RepositoryDayStatisticsService.byRepository(repository);
        assertTrue(service.dayStatistics().blockFirst() instanceof DayStatistics);
    }
}