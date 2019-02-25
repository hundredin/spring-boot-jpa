package com.spring.common.repository;

import com.spring.common.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Comment, Long> {
}
