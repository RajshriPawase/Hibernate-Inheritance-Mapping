package com.HibernateDemo;

import java.util.List;

import com.HibernateDemo.EmployeeTest.searchCriteria;

public interface EmployeeData {
	Employee getEmployee(int empId)throws Exception;
	boolean addEmployee(Employee emp)throws Exception;
	boolean deleteEmployee(int empId)throws Exception;
	boolean updateEmployee(Employee emp)throws Exception;
	public List<Employee> getAllEmployee()throws Exception;
	public List<Employee> searchEmployee(Employee emp, searchCriteria param)throws Exception;
	public List<Employee> searchEmployee(Employee emp, searchCriteria ...param)throws Exception;
}
