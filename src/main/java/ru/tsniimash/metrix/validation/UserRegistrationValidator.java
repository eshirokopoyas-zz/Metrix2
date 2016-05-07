package ru.tsniimash.metrix.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.tsniimash.metrix.model.User;

@Component
public class UserRegistrationValidator implements Validator
{
	@Autowired
	@Qualifier("emailValidator")
	private EmailValidator emailValidator;
	
	@Override
	public boolean supports(Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		User user = (User) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
		
		if (!user.getEmail().isEmpty()&&!emailValidator.valid(user.getEmail()))
		{
			errors.rejectValue("email", "Pattern.email");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
	}

}
