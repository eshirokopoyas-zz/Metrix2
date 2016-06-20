package ru.tsniimash.metrix.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.pojo.ExpertCount;
import ru.tsniimash.metrix.pojo.ProjectClusters;
import ru.tsniimash.metrix.pojo.ProjectCount;
import ru.tsniimash.metrix.service.ClusterisationService;
import ru.tsniimash.metrix.service.CriterionService;
import ru.tsniimash.metrix.service.ExpertService;
import ru.tsniimash.metrix.service.GradeService;
import ru.tsniimash.metrix.service.ProjectService;
import ru.tsniimash.metrix.service.UserService;

@Controller
public class SystemController
{
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(SystemController.class);
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ExpertService expertService;
	
	@Resource
	private CriterionService criterionService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private GradeService gradeService;
	
	@Resource
	private ClusterisationService clusterisationService;
	
	
	@RequestMapping(value = {"/first/{id}"}, method = RequestMethod.GET)
	public String settings(Model model,@PathVariable("id") long id)
	{
		User user = userService.findById(id);
		model.addAttribute("userid", id);
		model.addAttribute("projects_count", projectService.getProjectCountForUser(user));
		model.addAttribute("experts_count", expertService.getExpertCountForUser(user));
		model.addAttribute("criteria_count", criterionService.getCriterionCountForUser(user));
		return "first";
	}
	
	@RequestMapping(value = {"/second/{id}"}, method = RequestMethod.GET)
	public String input(Model model,@PathVariable("id") long id)
	{
		User user = userService.findById(id);
		model.addAttribute("experts", expertService.getExpertsForUser(user));
		model.addAttribute("projects", projectService.getProjectsForUser(user));
		model.addAttribute("criteria", criterionService.getCriteriaForUser(user));
		return "second";
	}
		
	//TEST
	@RequestMapping(value = {"/second2/{id}"}, method = RequestMethod.GET)
	public String secondStep(Model model,@PathVariable("id") long id)
	{
		boolean nextButton = false;
		User user = userService.findById(id);
		List<ExpertCount> resExpertCounts = new ArrayList<ExpertCount>();
		List<ProjectCount> projectCounts = new ArrayList<ProjectCount>();
		List<ProjectCount> syncProjectCounts = Collections.synchronizedList(projectCounts);
		List<Project> userProjects = projectService.getProjectsForUser(user); 
		List<Expert> userExperts = expertService.getExpertsForUser(user);
		final long projectCountNeed = criterionService.getCriterionCountForUser(user);
		final long expertCountNeed = projectCountNeed*projectService.getProjectCountForUser(user);
		List<ExpertCount> expertCounts = gradeService.getExpertsCount(user, expertCountNeed, projectCountNeed);
		
		expertCounts.forEach(x -> 
		{
			List<ProjectCount> expertProjectCounts = x.getProjectCounts();
			List<ProjectCount> tempProjectCounts = new ArrayList<ProjectCount>();
			userProjects.stream().filter(y -> expertProjectCounts.stream().noneMatch(z -> z.getProject().getId()==y.getId()))
			.forEach(y ->
			{
				ProjectCount projectCount = new ProjectCount();
				projectCount.setProject(y);
				projectCount.setExpert(x.getExpert());
				projectCount.setProjectGradeCount(0);
				projectCount.setProjectGradeCountNeed(projectCountNeed);
				tempProjectCounts.add(projectCount);
			});
			x.setProjectCounts(Stream.concat(expertProjectCounts.stream(), tempProjectCounts.stream()).collect(Collectors.toList()));
		});
		
		userProjects.forEach(z ->
		{
			ProjectCount projectCount = new ProjectCount();
			projectCount.setProject(z);
			projectCount.setProjectGradeCount(0);
			projectCount.setProjectGradeCountNeed(projectCountNeed);
			syncProjectCounts.add(projectCount);
		});
		
		List<ExpertCount> tempExpertCounts = new ArrayList<ExpertCount>();
		userExperts.stream().filter(x -> expertCounts.stream().noneMatch(y -> y.getExpert().getId()==x.getId())).forEach(x ->
		{
			ExpertCount expertCount = new ExpertCount();
			expertCount.setExpert(x);
			expertCount.setExpertGradeCount(0);
			expertCount.setProjectCounts(syncProjectCounts);
			expertCount.setExpertGradeCountNeed(expertCountNeed);
			tempExpertCounts.add(expertCount);
		});
		
		resExpertCounts = Stream.concat(expertCounts.stream(), tempExpertCounts.stream()).collect(Collectors.toList());
		
		nextButton = resExpertCounts.parallelStream().mapToLong(x -> x.getExpertGradeCount()).sum()==resExpertCounts.parallelStream().mapToLong(x -> x.getExpertGradeCountNeed()).sum();
			
		model.addAttribute("next", nextButton);
		model.addAttribute("expertsCount",resExpertCounts);
		model.addAttribute("userid", id);
		return "second2";
	}
	
	@RequestMapping(value = {"/fourth/{id}"}, method = RequestMethod.GET)
	public String fourthStep(Model model,@PathVariable("id") long id)
	{
		ProjectClusters projectClusters = clusterisationService.getProjectClusters(userService.findById(id));
		model.addAttribute("cluster1",projectClusters.getCluster1());
		model.addAttribute("cluster2",projectClusters.getCluster2());
		logger.log(Level.INFO, "got fourth");
		return "fourth";
	}
	
	@RequestMapping(value = {"/third/{id}"}, method = RequestMethod.GET)
	public String thirdStep(Model model,@PathVariable("id") long id)
	{
		model.addAttribute("groupGrades", clusterisationService.getGroupGrades(userService.findById(id)));
		model.addAttribute("userid", id);
		logger.log(Level.INFO, "got third");
		return "third";
	}
}

