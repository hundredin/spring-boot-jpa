package com.spring.common.repository;

import com.spring.common.config.SpringQueryDslRepositorySupport;
import com.spring.common.domain.QUser;
import com.spring.common.domain.User;

import java.util.List;

public class UserRepositoryImpl extends SpringQueryDslRepositorySupport implements UserRepositoryCustom {

	public UserRepositoryImpl(){
		super(User.class);
	}

	@Override public List<User> findCustom(String name) {

		QUser qUser = QUser.user;

		List<User> users = from(qUser)
				.where(qUser.name.eq(name))
				.fetch();

		return users;
	}
}
