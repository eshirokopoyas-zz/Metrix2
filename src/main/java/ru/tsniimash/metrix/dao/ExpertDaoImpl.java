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
import ru.tsniimash.metrix.model.User;

@Repository
public class ExpertDaoImpl implements ExpertDao
{
	private final Logger logger = Logger.getLogger(ExpertDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void addExpert(Expert expert)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(expert);
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
	public List<Expert> getExpertsForUser(User user)
	{
		List<Expert> experts = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			experts = session.createQuery("from Expert where user = :user").setLong("user", user.getId()).list();
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
		return experts;
	}

	@Override
	public Expert getExpert(long id)
	{
		Expert expert = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			expert = session.byId(Expert.class).getReference(id);
			
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
		return expert;
	}

	@Override
	public void deleteExpert(long id)
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.delete(session.get(Expert.class, id));
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
	public void updateExpert(Expert expert)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(expert);
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
	public Expert getExpertByEmail(String email)
	{
		Expert expert = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			expert = session.bySimpleNaturalId(Expert.class).getReference(email);
			
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
		return expert;
	}
}
