package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Overview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverviewRepository extends JpaRepository<Overview, Long> {
}
