package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
