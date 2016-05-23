package ru.tsniimash.metrix.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.CriterionDao;
import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.User;

@Service
public class CriterionServiceImpl implements CriterionService
{
	@Resource
	private CriterionDao criterionDao;

	@Override
	public void addCriterion(Criterion criterion)
	{
		criterionDao.addCriterion(criterion);
	}

	@Override
	public void updateCriterion(Criterion criterion)
	{
		criterionDao.updateCriterion(criterion);
	}

	@Override
	public List<Criterion> getCriteriaForUser(User user)
	{	
		return criterionDao.getCriteriaForUser(user);
	}

	@Override
	public Criterion getCriterion(long id)
	{
		return criterionDao.getCriterion(id);
	}

	@Override
	public Criterion getCriterionById(String criterionId)
	{
		return criterionDao.getCriterionById(criterionId);
	}

	@Override
	public void deleteCriterion(long id)
	{
		criterionDao.deleteCriterion(id);
	}

	@Override
	public int getCriterionCountForUser(User user)
	{
		return criterionDao.getCriterionCountForUser(user);
	}
}
