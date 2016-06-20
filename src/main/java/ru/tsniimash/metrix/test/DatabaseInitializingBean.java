package ru.tsniimash.metrix.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializingBean implements InitializingBean
{
	@SuppressWarnings("unused")
	private final String TEST = "TEST";
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void afterPropertiesSet() throws Exception
	{
/*		
		User user = new User();
		user.setEmail(TEST);
		user.setName(TEST);
		user.setPassword(TEST);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(user);
		transaction.commit();
		session.close();
*/		
	}

}
