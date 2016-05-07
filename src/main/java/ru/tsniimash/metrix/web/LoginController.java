package ru.tsniimash.metrix.web;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.tsniimash.metrix.model.SystemLogin;
import ru.tsniimash.metrix.service.SystemLoginService;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.LoginValidator;

@Controller
public class LoginController
{
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private SystemLoginService systemLoginService;
	
	@Resource
	private UserService userService;
	
	@Autowired
	private LoginValidator loginValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(loginValidator);
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginForm(Model model)
	{
		SystemLogin systemLogin = new SystemLogin();
		model.addAttribute("loginForm", systemLogin);
		return "login";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String login(@ModelAttribute("loginForm") @Validated SystemLogin systemLogin,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		if (result.hasErrors())
		{
			return "login";			
		}
		systemLogin.setUser(userService.findByEmail(systemLogin.getUser().getEmail()));
		systemLogin.setTimestamp(new Date());
		systemLoginService.save(systemLogin);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Вы вошли в систему!");
		return "redirect:/first/"+systemLogin.getUser().getId();
		
	}
}
