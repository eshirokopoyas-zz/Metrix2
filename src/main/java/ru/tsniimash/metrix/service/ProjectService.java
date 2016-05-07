package ru.tsniimash.metrix.service;

import java.util.List;

import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

public interface ProjectService
{
	public void addProject(Project project);
	
	public List<Project> getProjectsForUser(User user);
	
	public Project getProject(long id);
	
	public Project getProjectByProjectId(String project_id);
	
	public void deleteProject(long id);
}
