package com.HibernateDemo;


public class EmployeeTest {
	public enum searchCriteria{
		EMPID,EMPNAME,SALARY,ALL
	}

	public static void main(String[] args) throws Exception {
		Employee emp1 = new Employee(1,"Varsha", 1000);
		Employee emp2 = new Employee(2,"sachin", 15000);
		Employee emp3= new Employee(3,"vinay", 12000);
		EmployeeImpl e1 = new EmployeeImpl();
		
		/*System.out.println("Insert successfully: ");
		e1.addEmployee(emp1);
		e1.addEmployee(emp2);
		e1.addEmployee(emp3);
		System.out.println("get Employee reocrd: "+e1.getEmployee(6));
		System.out.println("Delete Employee: "+e1.deleteEmployee(2));
		emp1.setEmpName("Shri");
		
		System.out.println(e1.updateEmployee(emp1));*/
		//System.out.println(e1.getAllEmployee());
		System.out.println(e1.searchEmployee(emp1, searchCriteria.ALL));
		
		System.out.println(e1.searchEmployee(emp1,searchCriteria.EMPID, searchCriteria.SALARY));


	}

}
