package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.PageStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PageStatisticsRepository extends Repository<PageStatistics, Integer> {
    @Query("FROM PageStatistics WHERE report = (SELECT MAX(report) FROM PageStatistics)")
    List<PageStatistics> findAll();
}
