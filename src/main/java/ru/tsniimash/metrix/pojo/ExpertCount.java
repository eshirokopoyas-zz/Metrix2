package ru.tsniimash.metrix.pojo;

import java.util.List;

import ru.tsniimash.metrix.model.Expert;

public class ExpertCount
{
	private Expert expert;
	private List<ProjectCount> projectCounts;
	private long expertGradeCount;
	private long expertGradeCountNeed;
	public Expert getExpert()
	{
		return expert;
	}
	public void setExpert(Expert expert)
	{
		this.expert = expert;
	}
	public List<ProjectCount> getProjectCounts()
	{
		return projectCounts;
	}
	public void setProjectCounts(List<ProjectCount> projectCounts)
	{
		this.projectCounts = projectCounts;
	}
	public long getExpertGradeCount()
	{
		return expertGradeCount;
	}
	public void setExpertGradeCount(long expertGradeCount)
	{
		this.expertGradeCount = expertGradeCount;
	}
	public long getExpertGradeCountNeed()
	{
		return expertGradeCountNeed;
	}
	public void setExpertGradeCountNeed(long expertGradeCountNeed)
	{
		this.expertGradeCountNeed = expertGradeCountNeed;
	}
}
