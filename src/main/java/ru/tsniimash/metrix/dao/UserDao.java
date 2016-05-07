package ru.tsniimash.metrix.dao;

import java.util.List;

import ru.tsniimash.metrix.model.User;

public interface UserDao
{
	User findByEmail(String email);
	
	User findById(long id);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(long id);
}
