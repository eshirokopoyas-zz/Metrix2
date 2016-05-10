package ru.tsniimash.metrix.dao;

import java.util.List;

import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

public interface ProjectDao
{
	public void addProject(Project project);
	
	public void updateProject(Project project);
	
	public List<Project> getProjectsForUser(User user);
	
	public Project getProject(long id);
	
	public Project getProjectByProjectId(String project_id);
	
	public void deleteProject(long id);
}
