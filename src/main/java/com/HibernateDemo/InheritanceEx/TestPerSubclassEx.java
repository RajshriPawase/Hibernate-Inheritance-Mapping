package com.HibernateDemo.InheritanceEx;

import javax.persistence.Column;
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
 *
 *2. Table Per Subclass
 *			-->InheritanceType=JOINED
 *			-->XML Mapping= <joined- subclass>
 * 			-->One table for each class is created
 * 			-->Foreign key is maintained between the tables
 * 			-->Super class cannot be abstract or interface
 * 			-->There are no duplicate columns.
 * 			-->Advantages:
 * 					-Changes in a single super class does not demand complex changes in database
 * 					-Tables are normalized.
 * 			-->Disadvantages:
 * 					-For deep hierarchy, it may give poor performance
 *
 *Example: Program Output
 *	TPS_College
 *		+------------+--------------+    
		| College_Id | College_Name |
		+------------+--------------+
		|          2 | BGP          |
		|          3 | SCSCOE       |
		|          4 | AVCOE        |
		|          5 | SPS          |
		+------------+--------------+   
 *
 *TPS_EnggStudent
 *		+-------------+---------+--------------+------------+
		| Branch      | Roll_No | Student_Name | College_Id |
		+-------------+---------+--------------+------------+
		| Computer    |       1 | Rajshri      |          2 |
		| Electronics |       2 | Monali       |          3 |
		+-------------+---------+--------------+------------+
				
 *	
 *TPS_MedicalStudent
 *			+----------+---------+--------------+------------+
		| Field    | Roll_No | Student_Name | College_Id |
		+----------+---------+--------------+------------+
		| B.Pharma |      11 | Sonali       |          4 |
		| BAMS     |      21 | Kanchan      |          5 |
		+----------+---------+--------------+------------+
 *
 *
 */
public class TestPerSubclassEx {
public static void main(String[] args) throws HibernateException, MyException {
	Session session=HibernateUtil.getSessionFactory().openSession();
	Transaction trans=session.beginTransaction();
	CollegeInfo co=new CollegeInfo(101, "Ates");
	StudentInfo st=new StudentInfo(102, "BGP", 1, "Rajshri", "Computer");
	StudentInfo st1=new StudentInfo(103, "SCSCOE", 2, "Monali", "Electronics");
	StudentInfo1 st11=new StudentInfo1(104, "AVCOE", 11, "Sonali", "B.Pharma");
	StudentInfo1 st12=new StudentInfo1(105, "SPS", 21, "Kanchan", "BAMS");
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
@Table(name="TPS_College")
@Inheritance(strategy=InheritanceType.JOINED)
class CollegeInfo{
	@Id
	@Column(name="College_Id")
	@GeneratedValue(strategy=GenerationType.TABLE)
	int collegeId;
	@Column(name="College_Name")
	String collegeName;
	public CollegeInfo(int collegeId, String collegeName) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}
	public CollegeInfo() {
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
@Table(name="TPS_EnggStudent")
class StudentInfo extends CollegeInfo {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Branch")
	String branch;
	
	public StudentInfo(int collegeId, String collegeName, int rollNo, String studentName, String branch) {
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
@Table(name="TPS_MedicalStudent")
class StudentInfo1 extends CollegeInfo {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Field")
	String field;

	public StudentInfo1(int collegeId, String collegeName, int rollNo, String studentName, String field) {
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