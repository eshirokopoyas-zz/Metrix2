package ru.tsniimash.metrix.dao;

import java.util.List;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.User;

public interface ExpertDao
{
	public void addExpert(Expert expert);
	
	public void updateExpert(Expert expert);
	
	public List<Expert> getExpertsForUser(User user);
	
	public int getExpertCountForUser(User user);
	
	public Expert getExpert(long id);
	
	public Expert getExpertByEmail(String email);
	
	public void deleteExpert(long id);
}
