package com.github.wsmt.api.service.impl;

import com.github.wsmt.api.model.PageStatistics;
import com.github.wsmt.api.repository.PageStatisticsRepository;
import com.github.wsmt.api.service.PageStatisticsService;
import reactor.core.publisher.Flux;

public class RepositoryPageStatisticsService implements PageStatisticsService {
    private final PageStatisticsRepository pageStatisticsRepository;

    private RepositoryPageStatisticsService(PageStatisticsRepository pageStatisticsRepository) {
        this.pageStatisticsRepository = pageStatisticsRepository;
    }

    public static PageStatisticsService byRepository(PageStatisticsRepository pageStatisticsRepository) {
        return new RepositoryPageStatisticsService(pageStatisticsRepository);
    }

    @Override
    public Flux<PageStatistics> pageStatistics() {
        return Flux.fromIterable(
                pageStatisticsRepository.findAll()
        );
    }
}
