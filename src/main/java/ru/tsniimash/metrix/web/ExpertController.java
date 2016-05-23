package ru.tsniimash.metrix.web;

import java.util.Date;

import javax.annotation.Resource;

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
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.ExpertService;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.ExpertValidator;

@Controller
public class ExpertController
{
	private final Logger logger = Logger.getLogger(ExpertController.class);
	
	@Resource
	private ExpertValidator expertValidator;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ExpertService expertService;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(expertValidator);
	}

	@RequestMapping(value = "/experts/{userid}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("userid") long userid)
	{
		User user = userService.findById(userid);
		Expert expert = new Expert();
		expert.setUser(user);
		model.addAttribute("expertForm", expert);
		model.addAttribute("experts",expertService.getExpertsForUser(user));
		model.addAttribute("userid",userid);
		return "experts";
	}
	
	@RequestMapping(value = "/experts/{userid}", method = RequestMethod.POST)
	public String addProject(Model model, @PathVariable("userid") long userid, @ModelAttribute("expertForm") @Validated Expert expert, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		model.addAttribute("userid",userid);
		if (bindingResult.hasErrors())
		{
			model.addAttribute("experts",expertService.getExpertsForUser(userService.findById(userid)));
			return "experts";
		}
		expert.setCreated(new Date());
		expert.setUser(userService.findById(userid));
		expertService.addExpert(expert);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Эксперт успешно добавлен.");
		return "redirect:/experts/"+userid;	
	}
	
	@RequestMapping(value = "/experts/{userid}/{expertid}/update", method = RequestMethod.GET)
	public String updateProjectForm(Model model, @PathVariable("userid") long userid, @PathVariable("expertid") long expertid)
	{
		Expert expert = expertService.getExpert(expertid);
		model.addAttribute("userid",userid);
		model.addAttribute("expertForm", expert);
		model.addAttribute("experts",expertService.getExpertsForUser(userService.findById(userid)));
		return "experts";
		
	}
	
	@RequestMapping(value = "/experts/{userid}/{expertid}/update", method = RequestMethod.POST)
	public String updateProject(Model model, @PathVariable("userid") long userid, @PathVariable("expertid") long expertid, @ModelAttribute("expertForm") Expert expert, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		Expert expertToUpdate = expertService.getExpert(expertid);
		expertToUpdate.setModified(new Date());
		expertToUpdate.setName(expert.getName());
		expertService.updateExpert(expertToUpdate);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Эксперт успешно обновлён.");
		return "redirect:/experts/"+userid;
	}
	
	@RequestMapping(value = "/experts/{userid}/{expertid}/delete", method = RequestMethod.POST)
	public String deleteProject(@PathVariable("userid") long userid, @PathVariable("expertid") long expertid,final RedirectAttributes redirectAttributes)
	{
		expertService.deleteExpert(expertid);
		redirectAttributes.addFlashAttribute("css","success");
		redirectAttributes.addFlashAttribute("msg","Эксперт успешно удалён.");
		return "redirect:/experts/"+userid;
	}
}  
