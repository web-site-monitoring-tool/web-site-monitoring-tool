package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.BrowserStatistics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface BrowserStatisticsRepository extends Repository<BrowserStatistics, Integer> {
    @Query("FROM BrowserStatistics WHERE report = (SELECT MAX(report) FROM BrowserStatistics)")
    List<BrowserStatistics> findAll();
}