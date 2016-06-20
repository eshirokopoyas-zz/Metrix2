package ru.tsniimash.metrix.pojo.compare;

import java.util.Comparator;

import ru.tsniimash.metrix.pojo.ProjectVectorModule;

public class ProjectVectorModuleComparator implements Comparator<ProjectVectorModule>
{

	@Override
	public int compare(ProjectVectorModule o1, ProjectVectorModule o2)
	{
		return o1.getGradeMod().compareTo(o2.getGradeMod());
	}
	
}
