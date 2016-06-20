package ru.tsniimash.metrix.pojo;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Project;

public class Step2POJO
{
	private Expert expert;
	private Project project;
	private long projectGradeCount;
	public Expert getExpert()
	{
		return expert;
	}
	public void setExpert(Expert expert)
	{
		this.expert = expert;
	}
	public Project getProject()
	{
		return project;
	}
	public void setProject(Project project)
	{
		this.project = project;
	}
	public long getProjectGradeCount()
	{
		return projectGradeCount;
	}
	public void setProjectGradeCount(long projectGradeCount)
	{
		this.projectGradeCount = projectGradeCount;
	}
}
