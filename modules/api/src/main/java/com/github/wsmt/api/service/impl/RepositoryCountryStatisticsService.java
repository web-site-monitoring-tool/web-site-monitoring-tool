package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.CountryStatistics;
import com.github.wsmt.api.repository.CountryStatisticsRepository;
import com.github.wsmt.api.service.CountryStatisticsService;
import reactor.core.publisher.Flux;

public class RepositoryCountryStatisticsService implements CountryStatisticsService {

    private final CountryStatisticsRepository countryStatisticsRepository;

    private RepositoryCountryStatisticsService(CountryStatisticsRepository countryStatisticsRepository) {
        this.countryStatisticsRepository = countryStatisticsRepository;
    }

    public static CountryStatisticsService byRepository(CountryStatisticsRepository repository) {
        return new RepositoryCountryStatisticsService(repository);
    }

    @Override
    public Flux<CountryStatistics> countryStatistics() {
        return Flux.fromIterable(
                countryStatisticsRepository.findAll()
        );
    }
}
