package ru.tsniimash.metrix.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.tsniimash.metrix.model.Expert;
import ru.tsniimash.metrix.model.Grade;
import ru.tsniimash.metrix.model.Project;
import ru.tsniimash.metrix.model.User;

@Repository
public class GradeDaoImpl implements GradeDao
{
	private final Logger logger = Logger.getLogger(GradeDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getGradesForUser(User user)
	{
		List<Grade> grades = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			grades = session.createQuery("from Grade where user = :user").setLong("user", user.getId()).list();
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
		return grades;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getGradesForExpertAndProject(Expert expert, Project project)
	{
		List<Grade> grades = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			grades = session.createQuery("from Grade where expert = :expert and project = :project").setLong("expert", expert.getId()).setLong("project", project.getId()).list();
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
		return grades;
	}

	@Override
	public void saveGrade(Grade grade)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(grade);
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

	@Override
	public Grade getGradeById(long id)
	{
		Grade grade = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			grade = session.byId(Grade.class).getReference(id);
			
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
		return grade;
	}

	@Override
	public void deleteGrade(long id)
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.delete(session.get(Grade.class, id));
		}
		catch (Exception e)
		{
			logger.log(Level.ERROR, e);
		}
		finally
		{
			if (session!=null)
			{
				session.flush();
				session.close();
			}
		}
		
	}

}