package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.DayStatistics;
import com.github.wsmt.api.repository.DayStatisticsRepository;
import com.github.wsmt.api.service.DayStatisticsService;
import reactor.core.publisher.Flux;

public class RepositoryDayStatisticsService implements DayStatisticsService {
    private final DayStatisticsRepository dayStatisticsRepository;

    private RepositoryDayStatisticsService(DayStatisticsRepository dayStatisticsRepository) {
        this.dayStatisticsRepository = dayStatisticsRepository;
    }

    public static DayStatisticsService byRepository(DayStatisticsRepository dayStatisticsRepository) {
        return new RepositoryDayStatisticsService(dayStatisticsRepository);
    }

    @Override
    public Flux<DayStatistics> dayStatistics() {
        return Flux.fromIterable(
                dayStatisticsRepository.findAll()
        );
    }
}
