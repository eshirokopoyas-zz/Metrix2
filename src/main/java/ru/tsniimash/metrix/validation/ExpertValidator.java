package ru.tsniimash.metrix.validation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.service.ExpertService;

@Component
public class ExpertValidator implements Validator
{
	private final Logger logger = Logger.getLogger(ExpertValidator.class);
	
	@Resource
	private EmailValidator emailValidator;
	
	@Resource
	private ExpertService expertService;

	@Override
	public boolean supports(Class<?> clazz)
	{
		return Expert.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		Expert expert = (Expert) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.expertForm.email");
 
		String email = expert.getEmail();
		
		if (email.isEmpty())
		{
			return;
		}
		
		if (!emailValidator.valid(email))
		{
			errors.rejectValue("email", "Pattern.email");
		}
		
		if (expertService.getExpertByEmail(email)!=null)
		{
			errors.rejectValue("email", "Diff.expertForm.NotUniqueEmail");
		}
	}
}
