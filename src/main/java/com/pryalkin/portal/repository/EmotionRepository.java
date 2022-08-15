package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

}
