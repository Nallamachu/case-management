package com.softifyo.mgmt.service;

import java.util.List;
import java.util.Optional;

import com.softifyo.mgmt.entity.User;

public interface UserService {

	List<User> getAllUsers();

	Optional<User> getUserById(Long id);

	User saveUser(User user);

	void removeUser(Long id);

}
