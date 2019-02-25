package com.spring.common.repository;

import com.spring.common.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
	List<User> findCustom(String name);
}
