package com.HibernateDemo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionfactory = null;

	public static SessionFactory getSessionFactory() throws MyException{
		try{
		if (sessionfactory == null) 
			sessionfactory = new Configuration().configure().buildSessionFactory();
		}catch(Exception e)
		{
			//e.printStackTrace();
			throw new MyException("Hibernate Configuration File Error");
		}
		return sessionfactory;
	}
}
