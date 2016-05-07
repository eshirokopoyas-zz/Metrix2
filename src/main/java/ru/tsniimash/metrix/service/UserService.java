package ru.tsniimash.metrix.service;

import java.util.List;

import ru.tsniimash.metrix.model.User;

public interface UserService
{
	User findByEmail(String email);
	
	User findById(long id);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(long id);
}
