package ru.tsniimash.metrix.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.tsniimash.metrix.model.User;

@Repository
public class UserDaoImpl implements UserDao
{
	private final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public User findByEmail(String email)
	{
		User user = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			user = session.bySimpleNaturalId(User.class).load(email); 
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
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll()
	{
		List<User> users = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			users = session.createQuery("from User").list(); 
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
		return users;
	}

	@Override
	public void save(User user)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
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
	public void update(User user)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(user);
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
	public void delete(long id)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(session.get(User.class, id));
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
	public User findById(long id)
	{
		User user = null;
		Session session = null;
		try
		{
			session = sessionFactory.openSession();
			user = session.byId(User.class).getReference(id); 
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
		return user;
	}
	
}
