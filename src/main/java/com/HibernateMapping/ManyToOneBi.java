package com.HibernateMapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class ManyToOneBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Employee1 e1=new Employee1(1, "Raj", 12000, null);
		Employee1 e2=new Employee1(2, "Shri", 14000, null);
		Company1 c1=new Company1(101, "TCS", null);
		e1.setCompany(c1);
		e2.setCompany(c1);
		List<Employee1> emp=new ArrayList<Employee1>();
		emp.add(e1);
		emp.add(e2);
		c1.setEmp(emp);
		session.save(c1);
		trans.commit();
		session.clear();
		System.out.println("complete successfully");
	}

}
@Entity
@Table(name="MTOBi_Employee")
class Employee1{
	@Id
	@Column(name="Emp_Id")
	private int empId;
	@Column(name="Emp_Name")
	private String empName;
	@Column(name="Emp_salary")
	private long salary;
	@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="Company_Key")
	@JoinTable(name="Emp_Comp_Bi",joinColumns=@JoinColumn(name="Emp_Id"),inverseJoinColumns=@JoinColumn(name="Comp_Id"))
	private Company1 company;
	public Employee1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee1(int empId, String empName, long salary, Company1 company) {
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

	public Company1 getCompany() {
		return company;
	}

	public void setCompany(Company1 company) {
		this.company = company;
	}
	
}

@Entity
@Table(name="MTOBi_Comapny")
class Company1{
	@Id
	@Column(name="Comp_Id")
	private int compId;
	@Column(name="Comp_Name")
	private String comName;
	@OneToMany(mappedBy="company",cascade=CascadeType.ALL)
	List<Employee1> emp;
	public Company1(int compId, String comName, List<Employee1> emp) {
		super();
		this.compId = compId;
		this.comName = comName;
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "Company1 [compId=" + compId + ", comName=" + comName + ", emp=" + emp + "]";
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
	public List<Employee1> getEmp() {
		return emp;
	}
	public void setEmp(List<Employee1> emp) {
		this.emp = emp;
	}
	
	
}