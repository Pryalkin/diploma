package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Image;
import com.pryalkin.portal.exception.model.EmailExistException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
