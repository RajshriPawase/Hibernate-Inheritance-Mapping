package com.HibernateDemo.InheritanceEx;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;




/**
 * @author Sai
 *	We can map the inheritance hierarchy classes with the table of the database. 
 *There are three inheritance mapping techniques:
	1. Table Per Hierarchy or class
	2. Table Per Subclass
	3. Table Per Concrete class
	
 *	1. Table Per Class
 *			-->InheritanceType= SINGLE_TABLE
 *			-->XML Mapping= <subclass>
 * 			-->Only one table is created for all the classes
 * 			-->One Discriminator field is used to identify the class
 * 			-->Super class cannot be abstract or interface
 * 			-->No. of Columns=No. of fields into all classes + Discriminator field column
 * 			-->Advantages:
 * 					-Simplest to implement
 * 					-Gives maximum performance even for the classes involved in deep hierarchy
 * 			-->Disadvantages:
 * 					-Most of the column of table are nullable
 * 					-Tables are not normalized.
 * 					-Change in any subclass leads to change in structure of Table
 * 
 * 
 * Example: Program Output
 * 	+-----------------+------------+--------------+-------------+---------+--------------+----------+
| DTYPE           | College_Id | College_Name | Branch      | Roll_No | Student_Name | Field    |
+-----------------+------------+--------------+-------------+---------+--------------+----------+
| College         |          1 | Ates         | NULL        |    NULL | NULL         | NULL     |
| Engg. Student   |          2 | BGP          | Computer    |       1 | Rajshri      | NULL     |
| Engg. Student   |          3 | SCSCOE       | Electronics |       2 | Monali       | NULL     |
| Medical Student |          4 | AVCOE        | NULL        |      11 | Sonali       | B.Pharma |
| Medical Student |          5 | SPS          | NULL        |      21 | Kanchan      | BAMS     |
+-----------------+------------+--------------+-------------+---------+--------------+----------+
 
 */
public class TestPerClassEx {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		College co=new College(101, "Ates");
		Student st=new Student(102, "BGP", 1, "Rajshri", "Computer");
		Student st1=new Student(103, "SCSCOE", 2, "Monali", "Electronics");
		Student1 st11=new Student1(104, "AVCOE", 11, "Sonali", "B.Pharma");
		Student1 st12=new Student1(105, "SPS", 21, "Kanchan", "BAMS");
		session.save(co);
		session.save(st);
		session.save(st1);
		session.save(st11);
		session.save(st12);
		trans.commit();
		session.close();
		System.out.println("Complete Successfully");
	}

}


@Entity
@Table(name="TPC_College")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("College")
class College{
	@Id
	@Column(name="College_Id")
	@GeneratedValue(strategy=GenerationType.TABLE)
	int collegeId;
	@Column(name="College_Name")
	String collegeName;
	public College(int collegeId, String collegeName) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}
	public College() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + "]";
	}
	
}
@Entity
//@Table(name="StudentInfo")
@DiscriminatorValue("Engg. Student")
class Student extends College {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Branch")
	String branch;
	
	public Student(int collegeId, String collegeName, int rollNo, String studentName, String branch) {
		super(collegeId, collegeName);
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", studentName=" + studentName + ", address=" + branch + "]";
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
}

@Entity
@DiscriminatorValue("Medical Student")
class Student1 extends College {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Field")
	String field;

	public Student1(int collegeId, String collegeName, int rollNo, String studentName, String field) {
		super(collegeId, collegeName);
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.field = field;
	}
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}	
}