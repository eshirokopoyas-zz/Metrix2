package ru.tsniimash.metrix.dao;

import java.util.List;

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.User;

public interface CriterionDao
{
	public void addCriterion(Criterion criterion);
	
	public void updateCriterion(Criterion criterion);
	
	public List<Criterion> getCriteriaForUser(User user);
	
	public int getCriterionCountForUser(User user);
	
	public Criterion getCriterion(long id);
	
	public Criterion getCriterionById(String criterionId);
	
	public void deleteCriterion(long id);
}
