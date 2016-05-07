package ru.tsniimash.metrix.dao;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.tsniimash.metrix.model.SystemLogin;

@Repository
public class SystemLoginDaoImpl implements SystemLoginDao
{
	private final Logger logger = Logger.getLogger(SystemLoginDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	
	public void save(SystemLogin systemLogin)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(systemLogin);
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

}
