package ru.tsniimash.metrix.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.tsniimash.metrix.model.Grade;

@Component
public class GradeValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return Grade.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grade", "NotEmpty.gradeForm.grade");
		
		Grade grade = (Grade) object;
		if (grade.getGrade()!=null)
		{
			if (grade.getGrade().compareTo(BigDecimal.ZERO)==-1 || grade.getGrade().compareTo(BigDecimal.ONE)==1)
			{
				errors.rejectValue("grade", "Pattern.gradeForm.grade");
			}
		}
	}

}
