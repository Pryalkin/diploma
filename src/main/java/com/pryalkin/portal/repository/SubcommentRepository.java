package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Subcomment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcommentRepository extends JpaRepository<Subcomment, Long> {
}
