package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.OverviewStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverviewStatisticsRepository extends JpaRepository<OverviewStatistics, Long> {
}
