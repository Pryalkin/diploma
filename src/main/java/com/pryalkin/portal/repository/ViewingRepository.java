package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewingRepository extends JpaRepository<Viewing, Long> {
}
