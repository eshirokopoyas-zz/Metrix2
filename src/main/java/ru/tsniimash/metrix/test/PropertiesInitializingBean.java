package ru.tsniimash.metrix.test;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class PropertiesInitializingBean implements InitializingBean
{
private final Logger logger = Logger.getLogger(PropertiesInitializingBean.class);

	@Override
	public void afterPropertiesSet() throws Exception
	{
		logger.log(Level.INFO, System.getProperty("java.class.path"));
	}

}
