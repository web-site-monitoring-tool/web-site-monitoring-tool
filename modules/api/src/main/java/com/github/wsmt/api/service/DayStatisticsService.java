package com.github.wsmt.api.service;

import com.github.wsmt.api.model.DayStatistics;
import reactor.core.publisher.Flux;

public interface DayStatisticsService {
    Flux<DayStatistics> dayStatistics();
}
