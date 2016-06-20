package ru.tsniimash.metrix.dao;

import java.util.List;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

public interface GradeDao
{
	public List<Grade> getGradesForUser(User user);
	
	public List<Grade> getGradesForExpertAndProject(Expert expert, Project project);
	
	public void saveGrade(Grade grade);
	
	public Grade getGradeById(long id);
	
	public void deleteGrade(long id);
}
