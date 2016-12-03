package com.spring.repository;

import com.spring.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
	List<User> findCustom(String name);
}
