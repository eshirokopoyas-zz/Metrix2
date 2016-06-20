package ru.tsniimash.metrix.pojo;

import java.math.BigDecimal;

import ru.tsniimash.metrix.model.Project;

public class ProjectVectorModule
{
	private Project project;
	private BigDecimal gradeMod;
	
	public Project getProject()
	{
		return project;
	}
	public void setProject(Project project)
	{
		this.project = project;
	}
	public BigDecimal getGradeMod()
	{
		return gradeMod;
	}
	public void setGradeMod(BigDecimal gradeMod)
	{
		this.gradeMod = gradeMod;
	}
}
