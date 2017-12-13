package com.HibernateDemo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeImpl implements EmployeeData{
	Session session=HibernateUtil.getSessionFactory().openSession();
	Transaction trans=session.beginTransaction();
	public Employee getEmployee(int empId) {
		// TODO Auto-generated method stub
		return session.get(Employee.class,empId);
		
	}

	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		session.save(emp);
		return true;
	}

	public boolean deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		Employee e2=session.get(Employee.class, empId);
		session.delete(e2);
		
		return true;
		
	}
	public void updateEmployee(int empId) {
		// TODO Auto-generated method stub
		Employee e2=session.get(Employee.class, empId);
		e2.setEmpName("Rajshri");
		e2.setSalary(12000);
		session.update(e2);
		
		System.out.println("Updated successfully");
		
		
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void getAllEmployee() {
		// TODO Auto-generated method stub
	
		Criteria criteria=session.createCriteria(Employee.class);
		List<Employee> list=criteria.list();
		Iterator<Employee> itr=list.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			System.out.println(emp);
		}

	}
	public Employee searchEmployee(int empId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Employee> list=session.createQuery("From Employee").list();
		Iterator<Employee> itr=list.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			if(empId==emp.getEmpId())
			{
				System.out.println(emp.getEmpName());
				System.out.println(emp.getSalary());
			}
		}
		trans.commit();
		session.close();
		return null;
	}

	
}
