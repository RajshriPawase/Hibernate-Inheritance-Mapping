package com.HibernateDemo.StudentCrud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="StudInfo")
public class Student {
	@Id
	@Column(name="Roll_No")
	private int rollNo;
	private String studName;
	private String dept;
	private int marks;
	public Student(String studName, String dept, int marks) {
		super();
		this.rollNo = rollNo;
		this.studName = studName;
		this.dept = dept;
		this.marks = marks;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", studName=" + studName + ", dept=" + dept + ", marks=" + marks + "]";
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	

}
