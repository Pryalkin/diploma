package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Subcomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcommentRepository extends JpaRepository<Subcomment, Long> {
}
