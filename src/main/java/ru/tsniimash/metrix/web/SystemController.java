package ru.tsniimash.metrix.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.CriterionService;
import ru.tsniimash.metrix.service.ExpertService;
import ru.tsniimash.metrix.service.ProjectService;
import ru.tsniimash.metrix.service.UserService;

@Controller
public class SystemController
{
	private final Logger logger = Logger.getLogger(SystemController.class);
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ExpertService expertService;
	
	@Resource
	private CriterionService criterionService;
	
	@Resource
	private UserService userService;
	
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
		logger.log(Level.INFO, id);
		return "second";
	}
	
	@RequestMapping(value = {"/third/{id}"}, method = RequestMethod.GET)
	public String test(Model model,@PathVariable("id") long id)
	{
		User user = userService.findById(id);
		List<Grade> grades = new ArrayList<Grade>();
		model.addAttribute("experts", expertService.getExpertsForUser(user));
		model.addAttribute("projects", projectService.getProjectsForUser(user));
		model.addAttribute("criteria", criterionService.getCriteriaForUser(user));
		logger.log(Level.INFO, id);
		return "third";
	}
}
