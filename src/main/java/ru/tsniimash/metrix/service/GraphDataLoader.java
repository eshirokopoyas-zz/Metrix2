package ru.tsniimash.metrix.service;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.pojo.ProjectClusters;

@Service
public class GraphDataLoader
{
	private ProjectClusters projectClusters;

	public ProjectClusters getProjectClusters()
	{
		return projectClusters;
	}

	public void setProjectClusters(ProjectClusters projectClusters)
	{
		this.projectClusters = projectClusters;
	}
}
