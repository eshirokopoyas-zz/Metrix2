package ru.tsniimash.metrix.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.tsniimash.metrix.dao.GradeDao;
import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.pojo.ExpertCount;
import ru.tsniimash.metrix.pojo.ProjectCount;
import ru.tsniimash.metrix.pojo.Step2POJO;

@Service
public class GradeServiceImpl implements GradeService
{
	@Resource
	private GradeDao gradeDAO;

	@Override
	public List<Grade> getGradesForUser(User user)
	{
		return gradeDAO.getGradesForUser(user);
	}
	
	public List<Step2POJO> getStep2pojos(User user)
	{
		List<Step2POJO> step2pojos = new ArrayList<Step2POJO>();
		List<Grade> grades = getGradesForUser(user);
		grades.forEach(x -> 
		{
			Step2POJO step2pojo = new Step2POJO();
			step2pojo.setExpert(x.getExpert());
			step2pojo.setProject(x.getProject());
			step2pojo.setProjectGradeCount(grades.parallelStream().filter(y -> y.getExpert().equals(x.getExpert())&&y.getProject().equals(x.getProject())).count());
			step2pojos.add(step2pojo);			
		});
		return step2pojos;
	}
	
	public List<ExpertCount> getExpertsCount(User user, long expertCountNeed, long projectCountNeed)
	{
		ConcurrentHashMap<Long, ExpertCount> concurrentExpertMap = new ConcurrentHashMap<Long, ExpertCount>();
		ConcurrentHashMap<Long, ProjectCount> concurrentProjectMap = new ConcurrentHashMap<Long, ProjectCount>();
		getGradesForUser(user).forEach(x -> 
		{
			Expert expert = x.getExpert();
			if (concurrentExpertMap.containsKey(expert.getId()))
			{
				ExpertCount expertCount = concurrentExpertMap.get(expert.getId());
				expertCount.setExpertGradeCount(expertCount.getExpertGradeCount()+1);
			}
			else
			{
				ExpertCount expertCount = new ExpertCount();
				expertCount.setExpertGradeCount(1);
				expertCount.setExpert(expert);
				expertCount.setExpertGradeCountNeed(expertCountNeed);
				concurrentExpertMap.put(expert.getId(), expertCount);
			}
			Project project = x.getProject();
			if (concurrentProjectMap.containsKey(expert.getId()*100+project.getId()))
			{
				ProjectCount projectCount = concurrentProjectMap.get(expert.getId()*100+project.getId());
				projectCount.setProjectGradeCount(projectCount.getProjectGradeCount()+1);
			}
			else
			{
				ProjectCount projectCount = new ProjectCount();
				projectCount.setProjectGradeCount(1);
				projectCount.setExpert(expert);
				projectCount.setProject(project);
				projectCount.setProjectGradeCountNeed(projectCountNeed);
				concurrentProjectMap.put(expert.getId()*100+project.getId(), projectCount);
			}
		});
		
		return concurrentExpertMap.values().stream().peek(x ->
		{
			x.setProjectCounts(Collections.synchronizedList(concurrentProjectMap.values().stream().filter(y -> y.getExpert().getId()==x.getExpert().getId()).collect(Collectors.toList())));
		}).collect(Collectors.toList());
	}

	@Override
	public List<Grade> getGradesForExpertAndProject(Expert expert, Project project)
	{
		return gradeDAO.getGradesForExpertAndProject(expert, project);
	}

	@Override
	public void saveGrade(Grade grade)
	{
		gradeDAO.saveGrade(grade);
		
	}

	@Override
	public Grade getGradeById(long id)
	{
		return gradeDAO.getGradeById(id);
	}

	@Override
	public void deleteGrade(long id)
	{
		gradeDAO.deleteGrade(id);
	}

}
