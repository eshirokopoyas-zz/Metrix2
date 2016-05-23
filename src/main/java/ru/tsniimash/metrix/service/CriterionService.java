package ru.tsniimash.metrix.service;

import java.util.List;

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.User;

public interface CriterionService
{
	public void addCriterion(Criterion criterion);
	
	public void updateCriterion(Criterion criterion);
	
	public List<Criterion> getCriteriaForUser(User user);
	
	public int getCriterionCountForUser(User user);
	
	public Criterion getCriterion(long id);
	
	public Criterion getCriterionById(String criterionId);
	
	public void deleteCriterion(long id);
}
