package ru.tsniimash.metrix.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

@Repository
public class ProjectDaoImpl implements ProjectDao
{
	private final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void addProject(Project project)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(project);
			transaction.commit();
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (transaction!=null)
			{
				transaction.rollback();
			}
			if (session!=null)
			{
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectsForUser(User user)
	{
		List<Project> projects = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			projects = session.createQuery("from Project where user = :user").setLong("user", user.getId()).list();
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
			logger.log(Level.ERROR, "!");
		}
		finally
		{
			if (session!=null)
			{
				session.close();
			}
		}
		return projects;
	}

	@Override
	public Project getProject(long id)
	{
		Project project = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			project = session.byId(Project.class).getReference(id);
			
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (session!=null)
			{
				session.close();
			}
		}
		return project;
	}

	@Override
	public void deleteProject(long id)
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.delete(session.get(Project.class, id));
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (session!=null)
			{
				session.close();
			}
		}
		
	}

	@Override
	public Project getProjectByProjectId(String project_id)
	{
		Project project = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			project = session.bySimpleNaturalId(Project.class).getReference(project_id);
			
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (session!=null)
			{
				session.close();
			}
		}
		return project;
	}
}
