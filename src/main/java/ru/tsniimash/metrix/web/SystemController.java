package ru.tsniimash.metrix.web;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemController
{
	private final Logger logger = Logger.getLogger(SystemController.class);
	
	@RequestMapping(value = {"/first/{id}"}, method = RequestMethod.GET)
	public String metrix(Model model,@PathVariable("id") long id)
	{
		logger.log(Level.INFO, id);
		return "first";
	}
}
