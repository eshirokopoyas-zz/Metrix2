package ru.tsniimash.metrix.service;

import java.util.List;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.User;

public interface ExpertService
{
	public void addExpert(Expert expert);
	
	public List<Expert> getExpertsForUser(User user);
	
	public Expert getExpert(long id);
	
	public void deleteExpert(long id);
}
