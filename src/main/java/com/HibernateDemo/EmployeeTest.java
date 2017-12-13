package com.HibernateDemo;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee emp = new Employee(101, "Varsha", 1000);
		Employee emp1 = new Employee(102, "sachin", 15000);
		Employee emp2 = new Employee(103, "vinay", 12000);
		EmployeeImpl e1 = new EmployeeImpl();
		System.out.println("Insert data");
		e1.addEmployee(emp);
		e1.addEmployee(emp1);
		e1.addEmployee(emp2);
		System.out.println("Get data" + e1.getEmployee(2));
		System.out.println("Delete record"+e1.deleteEmployee(3));
		e1.updateEmployee(2);
		e1.getAllEmployee();
		e1.searchEmployee(2);

	}

}
