package com.HibernateDemo;

public interface EmployeeData {
	Employee getEmployee(int empId);
	boolean addEmployee(Employee emp);
	boolean deleteEmployee(int empId);
	void updateEmployee(int empId);
	void getAllEmployee();
	Employee searchEmployee(int empId);
}
