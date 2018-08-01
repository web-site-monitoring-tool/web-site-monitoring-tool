package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.CountryStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CountryStatisticsRepository extends Repository<CountryStatistics, Integer> {
    @Query("FROM CountryStatistics WHERE report = (SELECT MAX(report) FROM CountryStatistics)")
    List<CountryStatistics> findAll();
}
