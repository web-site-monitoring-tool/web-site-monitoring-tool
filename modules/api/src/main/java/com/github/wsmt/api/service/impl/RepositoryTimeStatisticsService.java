package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.TimeStatistics;
import com.github.wsmt.api.repository.TimeStatisticsRepository;
import com.github.wsmt.api.service.TimeStatisticsService;
import reactor.core.publisher.Flux;

public class RepositoryTimeStatisticsService implements TimeStatisticsService {
    private final TimeStatisticsRepository timeStatisticsRepository;

    private RepositoryTimeStatisticsService(TimeStatisticsRepository timeStatisticsRepository) {
        this.timeStatisticsRepository = timeStatisticsRepository;
    }

    public static TimeStatisticsService byRepository(TimeStatisticsRepository timeStatisticsRepository) {
        return new RepositoryTimeStatisticsService(timeStatisticsRepository);
    }

    @Override
    public Flux<TimeStatistics> timeStatistics() {
        return Flux.fromIterable(
                timeStatisticsRepository.findAll()
        );
    }
}
