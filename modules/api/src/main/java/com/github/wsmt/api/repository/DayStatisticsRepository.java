package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.DayStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DayStatisticsRepository extends Repository<DayStatistics, Integer> {
    @Query("FROM DayStatistics WHERE report = (SELECT MAX(report) FROM DayStatistics)")
    List<DayStatistics> findAll();
}
