package com.HibernateDemo;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.HibernateDemo.EmployeeTest.searchCriteria;

public class EmployeeImpl implements EmployeeData{
	
	public Employee getEmployee(int empId) throws MyException {
			Session session=HibernateUtil.getSessionFactory().openSession();
			Transaction trans=session.beginTransaction();
			if(empId<=0){
				throw new MyException("Identifier null");
			}
		Employee emp=null;
			try{
				emp=session.get(Employee.class, empId);
			}catch(Exception e)
			{
				trans.rollback();
				throw new MyException("record not present into database");
			}
			return emp;
	}
	public boolean addEmployee(Employee emp) throws MyException {
		checkAllNullException(emp);
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		try{
			session.save(emp);
		}catch(Exception e)
		{
			trans.rollback();
			throw new MyException("fail to add into database");
		}
		finally {
			resoursecleanup(session, trans);
		}
		return true;
	}
	private void resoursecleanup(Session session, Transaction trans) {
		// TODO Auto-generated method stub
		if(trans!=null)
		{
			trans.commit();
		}
		if(session!=null)
		{
			session.close();
		}
	}
	private void checkAllNullException(Employee emp) throws MyException {
		// TODO Auto-generated method stub
		if(null==emp || null==emp.getEmpName() || emp.getEmpName().trim().length()<1 || emp.getSalary()<=0){
			throw new MyException("Check the field, doesn't null");
		}
		
	}
	public boolean deleteEmployee(int empId) throws MyException {
		// TODO Auto-generated method stub
		if(empId<=0 || null==getEmployee(empId))
			throw new MyException("Identifier null or not present");
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		
			try{
				//Employee e1=session.get(Employee.class, empId);
				session.delete(getEmployee(empId));
			}catch(Exception e)
			{
				trans.rollback();
				throw new MyException("record not present into database");
			}
			finally {
				resoursecleanup(session, trans);

			}
		return true;
	}
	
	public List<Employee> getAllEmployee() throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		List<Employee> list=null;
		try{
			//Employee e1=session.get(Employee.class, empId);
			list=session.createQuery("from Employee").list();
		}catch(Exception e)
		{
			trans.rollback();
			throw new MyException("record not present into database");
		}
		return list;
	}
	public List<Employee> searchEmployee(Employee emp, searchCriteria param) throws MyException {
		// TODO Auto-generated method stub
		checkAllNullException(emp);
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		List<Employee> list=new ArrayList<Employee>();
		try{
			Criteria criteria=session.createCriteria(Employee.class);
			switch(param){
			case EMPID:
				list.add(getEmployee(emp.getEmpId()));
				break;
			case EMPNAME:
				criteria.add(Restrictions.eq("empName", emp.getEmpName()));
				list=criteria.list();
				break;
			case SALARY:
				criteria.add(Restrictions.eq("salary", emp.getSalary()));
				list=criteria.list();
				break;
			case ALL:
				list=getAllEmployee();
				break;
			default:
				throw new MyException("Invalid Search Criteria");
				
			}
		}catch(Exception e)
		{
			trans.rollback();
			throw new MyException("record not present into database");
		}
		finally {
			resoursecleanup(session, trans);

		}
		return list;
	}
	public boolean updateEmployee(Employee emp) throws MyException {
		// TODO Auto-generated method stub
		checkAllNullException(emp);
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		if(null==getEmployee(emp.getEmpId()))
		{
			throw new MyException("Identifier null or not present");
		}
			try{
				//Employee e1=session.get(Employee.class, empId);
				session.update(emp);
			}catch(Exception e)
			{
				trans.rollback();
				throw new MyException("record not present into database");
			}
			finally {
				resoursecleanup(session, trans);

			}
		return true;
	}
	public List<Employee> searchEmployee(Employee emp, searchCriteria... param) throws Exception {
		// TODO Auto-generated method stub
		checkAllNullException(emp);
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		List<Employee> list=new ArrayList<Employee>();
		Criteria criteria=session.createCriteria(Employee.class);
		try{
			for(EmployeeTest.searchCriteria searchparam:param)
			{
				if(searchparam==searchCriteria.EMPID)
				{
					list.add(session.get(Employee.class, emp.getEmpId()));
				}
				if(searchparam==searchCriteria.EMPNAME)
				{
					criteria.add(Restrictions.eq("empName", emp.getEmpName()));
				
				}
				if(searchparam==searchCriteria.SALARY)
				{
					criteria.add(Restrictions.eq("salary", emp.getSalary()));
					
				}
				if(searchparam==searchCriteria.ALL)
				{
					list=getAllEmployee();
				}
			}
			list = criteria.list();
		}catch(Exception e)
		{
			//trans.rollback();
			throw new MyException("record not present into database");
		}
		finally {
			resoursecleanup(session, trans);

		}
		return list;
	}
	
	
}
