package ru.tsniimash.metrix.web;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.ProjectService;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.ProjectValidator;

@Controller
public class ProjectController
{
	private final Logger logger = Logger.getLogger(ProjectController.class);
	
	@Resource
	private ProjectValidator projectValidator;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProjectService projectService;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(projectValidator);
	}

	@RequestMapping(value = "/projects/{userid}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("userid") long userid)
	{
		User user = userService.findById(userid);
		Project project = new Project();
		project.setUser(user);
		model.addAttribute("projectForm", project);
		model.addAttribute("projects",projectService.getProjectsForUser(user));
		model.addAttribute("userid",userid);
		logger.log(Level.INFO, "projects get");
		return "projects";
	}
	
	@RequestMapping(value = "/projects/{userid}", method = RequestMethod.POST)
	public String addProject(Model model, @PathVariable("userid") long userid, @ModelAttribute("projectForm") @Validated Project project, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		model.addAttribute("userid",userid);
		if (bindingResult.hasErrors())
		{
			model.addAttribute("projects",projectService.getProjectsForUser(userService.findById(userid)));
			return "projects";
		}
		project.setCreated(new Date());
		project.setUser(userService.findById(userid));
		projectService.addProject(project);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Проект успешно добавлен.");
		return "redirect:/projects/"+userid;	
	}
	
	@RequestMapping(value = "/projects/{userid}/{projectid}/update", method = RequestMethod.GET)
	public String updateProject(Model model, @PathVariable("userid") long userid, @PathVariable("projectid") long projectid)
	{
		Project project = projectService.getProject(projectid);
		model.addAttribute("userid",userid);
		model.addAttribute("projectForm", project);
		model.addAttribute("projects",projectService.getProjectsForUser(userService.findById(userid)));
		return "projects";
		
	}
}  
