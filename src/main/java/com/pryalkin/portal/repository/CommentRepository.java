package com.pryalkin.portal.repository;

import com.pryalkin.portal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
