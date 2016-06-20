package ru.tsniimash.metrix.service;

import java.util.List;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.pojo.ExpertCount;
import ru.tsniimash.metrix.pojo.Step2POJO;

public interface GradeService
{	
	public List<Grade> getGradesForUser(User user);
	
	public List<Step2POJO> getStep2pojos(User user);
	
	public List<ExpertCount> getExpertsCount(User user, long expertCountNeed, long projectCountNeed);
	
	public List<Grade> getGradesForExpertAndProject(Expert expert, Project project);
	
	public void saveGrade(Grade grade);
	
	public Grade getGradeById(long id);
	
	public void deleteGrade(long id);
	
}
