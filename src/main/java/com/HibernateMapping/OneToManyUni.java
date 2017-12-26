package com.HibernateMapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class OneToManyUni {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Department d1=new Department(1, "Computer");
		Department d2=new Department(2, "Civil");
		College c1=new College(101, "SCSCOE", null);
		List<Department> dept_List=new ArrayList<Department>();
		dept_List.add(d1);
		dept_List.add(d2);	
		c1.setDept(dept_List);
		session.save(c1);
		trans.commit();
		session.close();
		System.out.println("Complete successfully");
	}

}

@Entity
@Table(name="OTMU_College")
class College{
	@Id
	//@GeneratedValue
	@Column(name="College_Id")
	private int collegeId;
	@Column(name="College_Name")
	private String collegeName;
	@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="Colg_Foregin_Id")
//	@JoinTable(name="Colg_Dept",joinColumns=@JoinColumn(name="College_Id"),inverseJoinColumns=@JoinColumn(name="Dept_Id"))
	@PrimaryKeyJoinColumn
	private List<Department> dept;
	
	public College() {
		super();
	}

	public College(int collegeId, String collegeName, List<Department> dept) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + ", dept=" + dept + "]";
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public List<Department> getDept() {
		return dept;
	}

	public void setDept(List<Department> dept) {
		this.dept = dept;
	}
	
	
	
}

@Entity
@Table(name="OTMU_Department")
class Department{
	@Id
	//@GeneratedValue
	@Column(name="Dept_Id")
	int deptId;
	@Column(name="Dept_Name")
	String deptName;
	public Department(int deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}