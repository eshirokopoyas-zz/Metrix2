package ru.tsniimash.metrix.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.UserDao;
import ru.tsniimash.metrix.model.User;

@Service
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao userDao;

	@Override
	public User findByEmail(String email)
	{
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findAll()
	{
		return userDao.findAll();
	}

	@Override
	public void save(User user)
	{
		userDao.save(user);
	}

	@Override
	public void update(User user)
	{
		userDao.update(user);
	}

	@Override
	public void delete(long id)
	{
		userDao.delete(id);
	}

	@Override
	public User findById(long id)
	{
		
		return userDao.findById(id);
	}

}
