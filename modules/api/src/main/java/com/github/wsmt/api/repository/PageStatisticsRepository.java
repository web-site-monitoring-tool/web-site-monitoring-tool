package com.github.wsmt.api.repository;

import com.github.wsmt.api.model.PageStatistics;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PageStatisticsRepository extends Repository<PageStatistics, Integer> {
    List<PageStatistics> findAll();
}
