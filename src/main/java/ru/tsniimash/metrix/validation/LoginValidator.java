package ru.tsniimash.metrix.validation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ru.tsniimash.metrix.model.SystemLogin;
import ru.tsniimash.metrix.model.User;
import ru.tsniimash.metrix.service.UserService;

@Component
public class LoginValidator implements Validator
{
	@Resource
	private UserService userService;
	
	@Autowired
	@Qualifier("emailValidator")
	private EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz)
	{
		return SystemLogin.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		SystemLogin systemLogin = (SystemLogin) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "NotEmpty.userForm.password");
		
		User loggingUser = systemLogin.getUser();
		String email = loggingUser.getEmail();
		
		if (email.isEmpty())
		{
			return;
		}
		
		if (!emailValidator.valid(email))
		{
			errors.rejectValue("user.email", "Pattern.email");
			return;
		}
		
		User user = userService.findByEmail(email); 
		if (user==null)
		{
			errors.rejectValue("user.email", "Diff.loginform.noSuchUser");
			return;
		}
		if (!user.getPassword().isEmpty()&&!user.getPassword().equals(loggingUser.getPassword()))
		{
			errors.rejectValue("user.password", "Diff.loginform.incorrectPassword");
		}
	}

}
