package ru.tsniimash.metrix.web;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.UserService;
import ru.tsniimash.metrix.validation.UserRegistrationValidator;

@Controller
public class WebController
{
	private final Logger logger = Logger.getLogger(WebController.class);
	
	@Resource
	private UserService userService;
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder)
	{
		webDataBinder.setValidator(userRegistrationValidation);
	}
	
	@Resource
	private UserRegistrationValidator userRegistrationValidation;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String main(Model model)
	{
		return "redirect:/metrix";
	}
	
	@RequestMapping(value = {"/metrix"}, method = RequestMethod.GET)
	public String metrix(Model model)
	{
		return "new-home";
	}
	
	@RequestMapping(value = {"/reg"}, method = RequestMethod.GET)
	public String regform(Model model)
	{
		User user = new User();
		model.addAttribute("userForm", user);
		populateDefaultModel(model);

		return "reg";
	}
	
	@RequestMapping(value = {"/reg"}, method = RequestMethod.POST)
	public String register(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		if (result.hasErrors())
		{
			populateDefaultModel(model);
			return "reg";
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Пользователь создан. Войдите в систему под Вашими учётными данными.");
			user.setRegistration_datetime(new Date());
			userService.save(user);
		}
			return "redirect:/login";
	}
	
	// TEST
	
	@RequestMapping(value = {"/test"}, method = RequestMethod.GET)
	public String test(Model model)
	{
		logger.log(Level.INFO, "test get");
		User user = new User();
		model.addAttribute("userForm", user);

		return "test";
	}
	
	
	
	@RequestMapping(value = {"/test"}, method = RequestMethod.POST)
	public String test2(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes)
	{
		logger.log(Level.INFO, "test post");

		if (result.hasErrors())
		{
			return "test";
		}
		else
		{
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Вы зарегистрированы!");
			logger.log(Level.INFO, user.getEmail());
			logger.log(Level.INFO, user.getPassword());
			logger.log(Level.INFO, user.getId());
			userService.save(user);
		}
			return "redirect:/";
	}
	
	private void populateDefaultModel(Model model)
	{
		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("RU", "Россия");
		country.put("CN", "Китай");
		country.put("SG", "Сингапур");
		country.put("US", "Соединённые Штаты");
		model.addAttribute("countryList", country);

	}
}
