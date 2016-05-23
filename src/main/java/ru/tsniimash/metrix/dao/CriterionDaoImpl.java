package ru.tsniimash.metrix.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.tsniimash.metrix.model.Criterion;
import ru.tsniimash.metrix.model.User;

@Repository
public class CriterionDaoImpl implements CriterionDao
{
	private final Logger logger = Logger.getLogger(CriterionDaoImpl.class);

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void addCriterion(Criterion criterion)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(criterion);
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
	public void updateCriterion(Criterion criterion)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(criterion);
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
	public List<Criterion> getCriteriaForUser(User user)
	{
		List<Criterion> criteria = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Criterion where user = :user").setLong("user", user.getId()).list();
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
		return criteria;
	}

	@Override
	public Criterion getCriterion(long id)
	{
		Criterion criterion = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			criterion = session.byId(Criterion.class).getReference(id);
			
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
		return criterion;
	}

	@Override
	public Criterion getCriterionById(String criterionId)
	{
		Criterion criterion = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			criterion = session.bySimpleNaturalId(Criterion.class).getReference(criterionId);
			
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
		return criterion;
	}

	@Override
	public void deleteCriterion(long id)
	{
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			session.delete(session.get(Criterion.class, id));
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

	@Override
	public int getCriterionCountForUser(User user)
	{
		int count = 0;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			count = Long.class.cast(session.createQuery("select count(*) from Criterion where user = :user").setLong("user", user.getId()).uniqueResult()).intValue();
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
		return count;
	}
}
