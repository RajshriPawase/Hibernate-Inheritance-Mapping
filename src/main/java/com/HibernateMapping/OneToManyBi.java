package com.HibernateMapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class OneToManyBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Department1 d1=new Department1(1, "Computer", null);
		Department1 d2=new Department1(2, "Civil", null);
		College1 c1=new College1(101, "SCSCOE", null);
		List<Department1> deptList=new ArrayList<Department1>();
		d1.setCollege(c1);
		d2.setCollege(c1);
		deptList.add(d1);
		deptList.add(d2);
		c1.setDept(deptList);
		session.save(c1);
		trans.commit();
		session.close();
		System.out.println("Complete successfully");
	}

}

@Entity
@Table(name="OTMBi_College")
class College1{
	@Id
	//@GeneratedValue
	@Column(name="College_Id")
	private int collegeId;
	@Column(name="College_Name")
	private String collegeName;
	@OneToMany(mappedBy="college",cascade=CascadeType.ALL)
	private List<Department1> dept;
	
	public College1() {
		super();
	}

	public College1(int collegeId, String collegeName, List<Department1> dept) {
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

	public List<Department1> getDept() {
		return dept;
	}

	public void setDept(List<Department1> dept) {
		this.dept = dept;
	}
	
	
	
}

@Entity
@Table(name="OTMBi_Department")
class Department1{
	@Id
	//@GeneratedValue
	@Column(name="Dept_Id")
	private int deptId;
	@Column(name="Dept_Name")
	private String deptName;
	@ManyToOne
	@JoinColumn(name="College_Key")
	//@JoinTable(name="Colg_Dept_Bi",joinColumns=@JoinColumn(name="College_Id"),inverseJoinColumns=@JoinColumn(name="Dept_Id"))
	//@PrimaryKeyJoinColumn
	private College1 college;
	
	public Department1(int deptId, String deptName, College1 college) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.college = college;
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
	public College1 getCollege() {
		return college;
	}
	public void setCollege(College1 college) {
		this.college = college;
	}
	
}