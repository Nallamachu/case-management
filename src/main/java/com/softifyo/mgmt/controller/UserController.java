package com.softifyo.mgmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.softifyo.mgmt.entity.User;
import com.softifyo.mgmt.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(path = "/{id}")
	public Optional<User> getUserById(@RequestParam("id") Long id) {
		return userService.getUserById(id);
	}

	@PostMapping(path = "/save")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PostMapping(path = "/update/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.removeUser(id);
	}
}
