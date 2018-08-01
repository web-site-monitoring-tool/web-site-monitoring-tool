package com.github.wsmt.api.service;

import com.github.wsmt.api.model.CountryStatistics;
import reactor.core.publisher.Flux;

public interface CountryStatisticsService {
    Flux<CountryStatistics> countryStatistics();
}
