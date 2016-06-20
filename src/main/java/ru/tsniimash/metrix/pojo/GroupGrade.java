package ru.tsniimash.metrix.pojo;

import java.math.BigDecimal;

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.Project;

public class GroupGrade
{
	private Criterion criterion;
	private Project project;
	private BigDecimal grade;
	public Criterion getCriterion()
	{
		return criterion;
	}
	public void setCriterion(Criterion criterion)
	{
		this.criterion = criterion;
	}
	public Project getProject()
	{
		return project;
	}
	public void setProject(Project project)
	{
		this.project = project;
	}
	public BigDecimal getGrade()
	{
		return grade;
	}
	public void setGrade(BigDecimal grade)
	{
		this.grade = grade;
	}
}
