package ru.tsniimash.metrix.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.SystemLoginDao;
import ru.tsniimash.metrix.model.SystemLogin;

@Service
public class SystemLoginServiceImpl implements SystemLoginService
{
	@Resource
	private SystemLoginDao systemLoginDao;

	@Override
	public void save(SystemLogin systemLogin)
	{
		systemLoginDao.save(systemLogin);
	}

}
