package com.github.wsmt.api.service;

import com.github.wsmt.api.model.TimeStatistics;
import reactor.core.publisher.Flux;

public interface TimeStatisticsService {
    Flux<TimeStatistics> timeStatistics();
}
