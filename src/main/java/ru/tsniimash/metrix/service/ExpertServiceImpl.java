package ru.tsniimash.metrix.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.ExpertDao;
import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.User;

@Service
public class ExpertServiceImpl implements ExpertService
{
	@Resource
	private ExpertDao expertDao;

	@Override
	public void addExpert(Expert expert)
	{
		expertDao.addExpert(expert);
	}

	@Override
	public List<Expert> getExpertsForUser(User user)
	{
		return expertDao.getExpertsForUser(user);
	}

	@Override
	public Expert getExpert(long id)
	{
		return expertDao.getExpert(id);
	}

	@Override
	public void deleteExpert(long id)
	{
		expertDao.deleteExpert(id);
	}

	@Override
	public void updateExpert(Expert expert)
	{
		
		expertDao.updateExpert(expert);
	}

	@Override
	public Expert getExpertByEmail(String email)
	{
		return expertDao.getExpertByEmail(email);
	}
	
}
