package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Popularity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularityRepository extends JpaRepository<Popularity, Long> {
}
