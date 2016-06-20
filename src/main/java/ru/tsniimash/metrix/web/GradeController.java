package ru.tsniimash.metrix.web;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.CriterionService;
import ru.tsniimash.metrix.service.ExpertService;
import ru.tsniimash.metrix.service.GradeService;
import ru.tsniimash.metrix.service.ProjectService;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.GradeValidator;

@Controller
public class GradeController
{
	private final Logger logger = Logger.getLogger(GradeController.class);
	
	@Resource
	private GradeValidator gradeValidator;
	
	@Resource
	private GradeService gradeService;
	
	@Resource
	private ExpertService expertService;
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private CriterionService criterionService;
	
	@Resource
	private UserService userService;	
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(gradeValidator);
	}
	
	@RequestMapping(value = "/grades/{userid}/{expertid}/{projectid}", method = RequestMethod.GET)
	public String getGradesList(Model model,@PathVariable ("userid") long userid,@PathVariable ("expertid") long expertid, @PathVariable ("projectid") long projectid)
	{
		Expert expert = expertService.getExpert(expertid);
		User user = userService.findById(userid);
		Project project = projectService.getProject(projectid);
		setCriteriaChoice(model, user, expert, project);
		Grade grade = new Grade();
		model.addAttribute("gradeForm", grade);
		model.addAttribute("grades", gradeService.getGradesForExpertAndProject(expert, project));

		return "grades";
	}
	
	@RequestMapping(value = "/grades/{userid}/{expertid}/{projectid}", method = RequestMethod.POST)
	public String addProject(Model model, @PathVariable("userid") long userid, @PathVariable("expertid") long expertid, @PathVariable("projectid") long projectid, @ModelAttribute("gradeForm") @Validated Grade grade, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		model.addAttribute("userid",userid);
		Expert expert = expertService.getExpert(expertid);
		User user = userService.findById(userid);
		Project project = projectService.getProject(projectid);
		if (bindingResult.hasErrors())
		{
			setCriteriaChoice(model, user, expert, project);
			logger.log(Level.INFO, "ERROR!");
			return "grades";
		}
		grade.setCreated(new Date());
		grade.setExpert(expert);
		grade.setProject(project);
		grade.setUser(user);
		grade.setCriterion(criterionService.getCriterion(Long.parseLong(grade.getCriterionIdString())));
		gradeService.saveGrade(grade);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Оценка успешно добавлена.");
		return "redirect:/grades/"+userid+"/"+expertid+"/"+projectid;
	}
	
	@RequestMapping(value = "/grades/{userid}/{expertid}/{projectid}/{gradeid}/delete", method = RequestMethod.POST)
	public String deleteGrade(@PathVariable("userid") long userid,@PathVariable("expertid") long expertid,@PathVariable("projectid") long projectid, @PathVariable("gradeid") long gradeid,final RedirectAttributes redirectAttributes)
	{
		gradeService.deleteGrade(gradeid);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Оценка успешно удалена.");
		return "redirect:/grades/"+userid+"/"+expertid+"/"+projectid;
	}
	
	private void setCriteriaChoice(Model model, User user, Expert expert, Project project)
	{
		logger.log(Level.INFO, user.getId());
		logger.log(Level.INFO, expert.getId());
		logger.log(Level.INFO, project.getId());
		ConcurrentHashMap<String, String> criterionMap = new ConcurrentHashMap<String,String>();
		List<Grade> currentGrades = gradeService.getGradesForExpertAndProject(expert, project);
		logger.log(Level.INFO, currentGrades);
		criterionService.getCriteriaForUser(user).stream().filter(x -> currentGrades.stream().noneMatch(y -> y.getCriterion().getId()==x.getId())).forEach(z -> criterionMap.put(String.valueOf(z.getId()), criterionService.getDisplayName(z)));
		logger.log(Level.INFO, criterionMap);
		model.addAttribute("criteriaList", criterionMap);
	}
}
