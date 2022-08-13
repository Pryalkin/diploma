package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
