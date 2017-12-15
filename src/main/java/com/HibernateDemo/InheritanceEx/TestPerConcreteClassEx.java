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
 *	3. Table Per Concrete class
 *			-->InheritanceType=TABLE_PER_CLASS
 *			-->XML Mapping= <union-subclass>
 * 			-->One table for each concrete class (subclass) is created
 * 			-->Foreign key is not maintained
 * 			-->Super class can be also an abstract or interface
 * 			-->There are duplicate column added in subclass tables only
 * 			-->Advantages:
 * 					-Change in base class leads to changes in all tables of derived class
 * 					-Simple style to implement inheritance mapping
 * 			-->Disadvantages:
 * 					-Members of super class are placed in each subclass and thereby any change in the super class should be reflected in each subclass
 *
 *Example: Program Output
 *TPCC_College
 *		+------------+--------------+
		| College_Id | College_Name |
		+------------+--------------+
		|          1 | Ates         |
		+------------+--------------+
 *
 *TPCC_EnggStudent
 *
 *		+------------+--------------+-------------+---------+--------------+
		| College_Id | College_Name | Branch      | Roll_No | Student_Name |
		+------------+--------------+-------------+---------+--------------+
		|          2 | BGP          | Computer    |       1 | Rajshri      |
		|          3 | SCSCOE       | Electronics |       2 | Monali       |
		+------------+--------------+-------------+---------+--------------+
 *
 *TPCC_MedicalStudent
 *
 *		+------------+--------------+----------+---------+--------------+
		| College_Id | College_Name | Field    | Roll_No | Student_Name |
		+------------+--------------+----------+---------+--------------+
		|          4 | AVCOE        | B.Pharma |      11 | Sonali       |
		|          5 | SPS          | BAMS     |      21 | Kanchan      |
		+------------+--------------+----------+---------+--------------+
 *
 */
public class TestPerConcreteClassEx {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		CollegeInfo1 co=new CollegeInfo1(101, "Ates");
		EnggStudent st=new EnggStudent(102, "BGP", 1, "Rajshri", "Computer");
		EnggStudent st1=new EnggStudent(103, "SCSCOE", 2, "Monali", "Electronics");
		MedicalStudent st11=new MedicalStudent(104, "AVCOE", 11, "Sonali", "B.Pharma");
		MedicalStudent st12=new MedicalStudent(105, "SPS", 21, "Kanchan", "BAMS");
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
@Table(name="TPCC_College")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
class CollegeInfo1{
	@Id
	@Column(name="College_Id")
	@GeneratedValue(strategy=GenerationType.TABLE)
	int collegeId;
	@Column(name="College_Name")
	String collegeName;
	public CollegeInfo1(int collegeId, String collegeName) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}
	public CollegeInfo1() {
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
@Table(name="TPCC_EnggStudent")
class EnggStudent extends CollegeInfo1 {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Branch")
	String branch;
	
	public EnggStudent(int collegeId, String collegeName, int rollNo, String studentName, String branch) {
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
@Table(name="TPCC_MedicalStudent")
class MedicalStudent extends CollegeInfo1 {
	@Column(name="Roll_No")
	int rollNo;
	@Column(name="Student_Name")
	String studentName;
	@Column(name="Field")
	String field;

	public MedicalStudent(int collegeId, String collegeName, int rollNo, String studentName, String field) {
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