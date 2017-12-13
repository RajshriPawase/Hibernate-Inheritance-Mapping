package com.HibernateDemo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionfactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionfactory == null) {
			sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionfactory;
	}
}
