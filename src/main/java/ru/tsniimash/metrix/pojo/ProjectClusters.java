package ru.tsniimash.metrix.pojo;

import java.util.List;

public class ProjectClusters
{
	List<ProjectVectorModule> cluster1;
	List<ProjectVectorModule> cluster2;
	public List<ProjectVectorModule> getCluster1()
	{
		return cluster1;
	}
	public void setCluster1(List<ProjectVectorModule> cluster1)
	{
		this.cluster1 = cluster1;
	}
	public List<ProjectVectorModule> getCluster2()
	{
		return cluster2;
	}
	public void setCluster2(List<ProjectVectorModule> cluster2)
	{
		this.cluster2 = cluster2;
	}


}
