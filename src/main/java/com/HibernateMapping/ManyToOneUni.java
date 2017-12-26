package com.HibernateMapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class ManyToOneUni {
public static void main(String[] args) throws HibernateException, MyException {
	Session session=HibernateUtil.getSessionFactory().openSession();
	Transaction trans=session.beginTransaction();
	Company c1=new Company(1, "IBM");
	Employee emp1=new Employee(101, "Sai", 10000, c1);
	Employee emp2=new Employee(102, "Om", 12000, c1);
	session.save(emp1);
	session.save(emp2);
	trans.commit();
	session.close();
	System.out.println("Complete Successfully");
}
}

@Entity
@Table(name="MTOU_Employee")
class Employee{
	@Id
	@Column(name="Emp_Id")
	private int empId;
	@Column(name="Emp_Name")
	private String empName;
	@Column(name="Emp_salary")
	private long salary;
	@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="Company_Key")
	//@JoinTable(name="Emp_Comp_Uni",joinColumns=@JoinColumn(name="Emp_Id"),inverseJoinColumns=@JoinColumn(name="Comp_Id"))
	@PrimaryKeyJoinColumn
	private Company company;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empId, String empName, long salary, Company company) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.company = company;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}

@Entity
@Table(name="MTOU_Comapny")
class Company{
	@Id
	@Column(name="Comp_Id")
	private int compId;
	@Column(name="Comp_Name")
	private String comName;
	public Company(int compId, String comName) {
		super();
		this.compId = compId;
		this.comName = comName;
	}
	@Override
	public String toString() {
		return "Company [compId=" + compId + ", comName=" + comName + "]";
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	
}