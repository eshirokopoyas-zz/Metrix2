package ru.tsniimash.metrix.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.ProjectDao;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

@Service
public class ProjectServiceImpl implements ProjectService
{
	@Resource
	private ProjectDao projectDao;

	@Override
	public void addProject(Project project)
	{
		projectDao.addProject(project);
	}

	@Override
	public List<Project> getProjectsForUser(User user)
	{
		return projectDao.getProjectsForUser(user);
	}

	@Override
	public Project getProject(long id)
	{
		return projectDao.getProject(id);
	}

	@Override
	public void deleteProject(long id)
	{
		projectDao.deleteProject(id);
	}

	@Override
	public Project getProjectByProjectId(String project_id)
	{
		return projectDao.getProjectByProjectId(project_id);
	}

}
