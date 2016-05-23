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

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.CriterionService;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.CriterionValidator;

@Controller
public class CriterionController
{
	private final Logger logger = Logger.getLogger(CriterionController.class);
	
	@Resource
	private CriterionValidator criterionValidator;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CriterionService criterionService;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(criterionValidator);
	}

	@RequestMapping(value = "/criteria/{userid}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("userid") long userid)
	{
		User user = userService.findById(userid);
		Criterion criterion = new Criterion();
		criterion.setUser(user);
		model.addAttribute("criterionForm", criterion);
		model.addAttribute("criteria",criterionService.getCriteriaForUser(user));
		model.addAttribute("userid",userid);
		return "criteria";
	}
	
	@RequestMapping(value = "/criteria/{userid}", method = RequestMethod.POST)
	public String addProject(Model model, @PathVariable("userid") long userid, @ModelAttribute("criterionForm") @Validated Criterion criterion, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		model.addAttribute("userid",userid);
		logger.log(Level.INFO, criterion.getId());
		if (bindingResult.hasErrors())
		{
			model.addAttribute("projects",criterionService.getCriteriaForUser(userService.findById(userid)));
			return "criteria";
		}
		criterion.setCreated(new Date());
		criterion.setUser(userService.findById(userid));
		criterionService.addCriterion(criterion);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Критерий успешно добавлен.");
		return "redirect:/criteria/"+userid;	
	}
	
	@RequestMapping(value = "/criteria/{userid}/{id}/update", method = RequestMethod.GET)
	public String updateProjectForm(Model model, @PathVariable("userid") long userid, @PathVariable("id") long criterionId)
	{
		Criterion criterion = criterionService.getCriterion(criterionId);
		model.addAttribute("userid",userid);
		model.addAttribute("criterionForm", criterion);
		model.addAttribute("criteria",criterionService.getCriteriaForUser(userService.findById(userid)));
		return "criteria";
		
	}
	
	@RequestMapping(value = "/criteria/{userid}/{id}/update", method = RequestMethod.POST)
	public String updateProject(Model model, @PathVariable("userid") long userid, @PathVariable("id") long criterionId, @ModelAttribute("criterionForm") Criterion criterion, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		Criterion criterionToUpdate = criterionService.getCriterion(criterionId);
		criterionToUpdate.setModified(new Date());
		criterionToUpdate.setName(criterion.getName());
		criterionService.updateCriterion(criterionToUpdate);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Критерий успешно обновлён.");
		return "redirect:/criteria/"+userid;
		
	}
	
	@RequestMapping(value = "/criteria/{userid}/{id}/delete", method = RequestMethod.POST)
	public String deleteProject(@PathVariable("userid") long userid, @PathVariable("id") long criterionId,final RedirectAttributes redirectAttributes)
	{
		criterionService.deleteCriterion(criterionId);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Критерий успешно удалён.");
		return "redirect:/criteria/"+userid;
	}
}  
