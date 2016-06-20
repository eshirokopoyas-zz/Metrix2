package ru.tsniimash.metrix.validation;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.service.CriterionService;

@Component
public class CriterionValidator implements Validator
{
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(CriterionValidator.class);
	
	@Resource
	private CriterionService criterionService;

	@Override
	public boolean supports(Class<?> clazz)
	{
		return Criterion.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		Criterion criterion = (Criterion) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "criterion_id", "NotEmpty.criterionForm.id");
 
		String criterion_id = criterion.getCriterion_id();
		
		if (criterion_id.isEmpty())
		{
			return;
		}
		
		if (criterionService.getCriterionById(criterion_id)!=null)
		{
			errors.rejectValue("criterion_id", "Diff.criterionForm.NotUniqueId");
		}
	}
}
