package com.spring.common.service;

import com.spring.common.repository.UserRepository;
import com.spring.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	@Autowired
    UserRepository userRepository;

	@Transactional
	public void testService() {
		User user = new User("foobar");
		userRepository.save(user);

		List<User> users = userRepository.findByName("foobar");
		System.out.println(users);

	}
}
