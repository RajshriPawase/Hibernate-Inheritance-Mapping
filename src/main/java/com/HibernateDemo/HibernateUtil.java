package com.HibernateDemo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionfactory = null;

	public static SessionFactory getSessionFactory() throws MyException{
		try{
		if (sessionfactory == null) 
			sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Exception e)
		{
			throw new MyException("Hibernate Configuration File Error");
		}
		return sessionfactory;
	}
}
