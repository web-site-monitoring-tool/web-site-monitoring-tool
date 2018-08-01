package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.TimeStatistics;
import com.github.wsmt.api.repository.TimeStatisticsRepository;
import com.github.wsmt.api.service.TimeStatisticsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTimeStatisticsServiceTest {
    @Test
    public void byRepository() {
        TimeStatisticsRepository repository = mock(TimeStatisticsRepository.class);
        TimeStatisticsService service = RepositoryTimeStatisticsService.byRepository(repository);
        assertNotNull(service);
    }

    @Test
    public void browserStatistics() {
        TimeStatisticsRepository repository = mock(TimeStatisticsRepository.class);
        when(repository.findAll())
                .thenReturn(new ArrayList<TimeStatistics>(
                        Arrays.asList(
                                new TimeStatistics(),
                                new TimeStatistics()
                        )
                ));
        TimeStatisticsService service = RepositoryTimeStatisticsService.byRepository(repository);
        assertTrue(service.timeStatistics().blockFirst() instanceof TimeStatistics);
    }
}