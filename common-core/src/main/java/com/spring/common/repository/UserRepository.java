package com.spring.common.repository;

import com.spring.common.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	List<User> findByName(String name);
}
