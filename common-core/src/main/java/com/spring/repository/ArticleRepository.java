package com.spring.repository;

import com.spring.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Comment, Long> {
}
