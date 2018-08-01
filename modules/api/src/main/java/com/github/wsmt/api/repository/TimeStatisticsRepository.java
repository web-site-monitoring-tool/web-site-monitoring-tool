package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.TimeStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TimeStatisticsRepository extends Repository<TimeStatistics, Integer> {
    @Query("FROM TimeStatistics WHERE report = (SELECT MAX(report) FROM TimeStatistics)")
    List<TimeStatistics> findAll();
}
