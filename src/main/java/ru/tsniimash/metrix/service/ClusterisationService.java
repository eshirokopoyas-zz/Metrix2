package ru.tsniimash.metrix.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.pojo.GroupGrade;
import ru.tsniimash.metrix.pojo.ProjectClusters;
import ru.tsniimash.metrix.pojo.ProjectVectorModule;
import ru.tsniimash.metrix.pojo.compare.ProjectVectorModuleComparator;

@Service
public class ClusterisationService
{
	@Resource
	private GradeService gradeService;
	
	@Resource
	private ExpertService expertService;
	
	@Resource
	private GraphDataLoader graphDataLoader;
	
	public List<GroupGrade> getGroupGrades(User user)
	{
		List<GroupGrade> userGroupGrades = new ArrayList<GroupGrade>();
		final long expertCount = expertService.getExpertCountForUser(user);
		gradeService.getGradesForUser(user).stream().forEach(x -> 
		{
			if (userGroupGrades.stream().anyMatch(y -> y.getCriterion().getId()==x.getCriterion().getId()&&y.getProject().getId()==x.getProject().getId()))
			{
				GroupGrade groupGrade = userGroupGrades.stream().filter(y -> y.getCriterion().getId()==x.getCriterion().getId()&&y.getProject().getId()==x.getProject().getId()).findFirst().get();
				groupGrade.setGrade(x.getGrade().add(groupGrade.getGrade()));
			}
			else
			{
				GroupGrade groupGrade = new GroupGrade();
				groupGrade.setCriterion(x.getCriterion());
				groupGrade.setProject(x.getProject());
				groupGrade.setGrade(x.getGrade());
				userGroupGrades.add(groupGrade);
			}
		});
		userGroupGrades.forEach(x -> x.setGrade(x.getGrade().divide(BigDecimal.valueOf(expertCount))));
		
		return userGroupGrades;
	}
	
	public List<ProjectVectorModule> getProjectVectorModules(User user)
	{
		List<ProjectVectorModule> res = new ArrayList<ProjectVectorModule>();
		getGroupGrades(user).stream().forEach(x -> 
		{
			if (res.stream().anyMatch(y -> y.getProject().getId()==x.getProject().getId()))
			{
				ProjectVectorModule projectVectorModule = res.stream().filter(y -> y.getProject().getId()==x.getProject().getId()).findFirst().get();
				projectVectorModule.setGradeMod(x.getGrade().add(projectVectorModule.getGradeMod().pow(2)));
			}
			else
			{
				ProjectVectorModule projectVectorModule = new ProjectVectorModule();
				projectVectorModule.setProject(x.getProject());
				projectVectorModule.setGradeMod(x.getGrade().pow(2));
				res.add(projectVectorModule);
			}
		});
		res.forEach(x -> x.setGradeMod(BigDecimal.valueOf(Math.sqrt(x.getGradeMod().doubleValue()))));
		System.out.println(res);
		
		return res;
	}
	
	public ProjectVectorModule getMaxProjectVectorModule(User user)
	{
		return getProjectVectorModules(user).stream().max(new ProjectVectorModuleComparator()).get();
	}
	public ProjectVectorModule getMinProjectVectorModule(User user)
	{
		return getProjectVectorModules(user).stream().min(new ProjectVectorModuleComparator()).get();
	}
	
	public ProjectClusters getProjectClusters(User user)
	{
		ProjectClusters projectClusters = new ProjectClusters();
		List<ProjectVectorModule> cluster1 = new ArrayList<ProjectVectorModule>();
		ProjectVectorModule max = getMaxProjectVectorModule(user);
		cluster1.add(max);
		BigDecimal cluster1Aprior = max.getGradeMod();
		
		List<ProjectVectorModule> cluster2 = new ArrayList<ProjectVectorModule>();
		ProjectVectorModule min = getMinProjectVectorModule(user);
		cluster2.add(min);
		BigDecimal cluster2Aprior = min.getGradeMod();
		
		
		for (ProjectVectorModule projectVectorModule:getProjectVectorModules(user))
		{
			if (projectVectorModule.getProject().getId()!=max.getProject().getId()&&projectVectorModule.getProject().getId()!=min.getProject().getId())
			{
				BigDecimal toCluster1 = projectVectorModule.getGradeMod().subtract(cluster1Aprior).pow(2);
				BigDecimal toCluster2 = projectVectorModule.getGradeMod().subtract(cluster2Aprior).pow(2);
				
				if (toCluster1.compareTo(toCluster2)==-1)
				{
					cluster1.add(projectVectorModule);
					cluster1Aprior = cluster1Aprior.add(projectVectorModule.getGradeMod()).divide(BigDecimal.valueOf(2));
				}
				else
				{
					cluster2.add(projectVectorModule);
					cluster2Aprior = cluster2Aprior.add(projectVectorModule.getGradeMod()).divide(BigDecimal.valueOf(2));
				}
			}
		}
		
		projectClusters.setCluster1(cluster1);
		projectClusters.setCluster2(cluster2);
		
		graphDataLoader.setProjectClusters(projectClusters);
		
		return projectClusters;
	}
	
}
