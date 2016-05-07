package ru.tsniimash.metrix.validation;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.service.ProjectService;

@Component
public class ProjectValidator implements Validator
{
	@Resource
	private ProjectService projectService;

	@Override
	public boolean supports(Class<?> clazz)
	{
		return Project.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		Project project = (Project) object;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "project_id", "NotEmpty.projectForm.id");
 
		String project_id = project.getProject_id();
		
		if (project_id.isEmpty())
		{
			return;
		}
		
		if (projectService.getProjectByProjectId(project_id)!=null)
		{
			errors.rejectValue("project_id", "Diff.projectForm.NotUniqueId");
		}
	}
}
