package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.CountryStatistics;
import com.github.wsmt.api.repository.CountryStatisticsRepository;
import com.github.wsmt.api.service.CountryStatisticsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryCountryStatisticsServiceTest {

    @Test
    public void byRepository() {
        CountryStatisticsRepository repository = mock(CountryStatisticsRepository.class);
        CountryStatisticsService service = RepositoryCountryStatisticsService.byRepository(repository);
        assertNotNull(service);
    }

    @Test
    public void browserStatistics() {
        CountryStatisticsRepository repository = mock(CountryStatisticsRepository.class);
        when(repository.findAll())
                .thenReturn(new ArrayList<CountryStatistics>(
                        Arrays.asList(
                                new CountryStatistics(),
                                new CountryStatistics()
                        )
                ));
        CountryStatisticsService service = RepositoryCountryStatisticsService.byRepository(repository);
        assertTrue(service.countryStatistics().blockFirst() instanceof CountryStatistics);
    }
}