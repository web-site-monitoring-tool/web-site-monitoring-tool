package com.github.wsmt.api.service;

import com.github.wsmt.api.model.PageStatistics;
import reactor.core.publisher.Flux;

public interface PageStatisticsService {
    Flux<PageStatistics> pageStatistics();
}
