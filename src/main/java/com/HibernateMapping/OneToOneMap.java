package com.HibernateMapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class OneToOneMap {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Address ad=new Address(1,"Pune", 422605);
		Address ad1=new Address(2,"Sangamner", 422621);
		Student st=new Student(101, "Anu", ad);
		Student st1=new Student(102, "vidya", ad1);
		session.save(st);
		session.save(st1);
		//session.save(ad);--->without cascade
		/*When using  @PrimaryKeyJoinColumn
		 
		 st.setRollNo(ad.getAddressId());
		session.save(st);
		ad1.setAddressId(st1.getRollNo());
		session.save(st1);*/
		
		trans.commit();
		session.close();
		System.out.println("Complete successfully");
	}

}


@Entity
@Table(name="OTO_Student")
class Student{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="Roll_No")
	private int RollNo;
	@Column(name="Student_Name")
	private String studName;
	
	@OneToOne(cascade=CascadeType.ALL)
	//1.Using foreign key association:
	//@JoinColumn(name="Address_Key")
	/*
	* 2.Using a common join table:
	  @JoinTable(name="OTO_Join",joinColumns=@JoinColumn(name="Roll_No"),
					inverseJoinColumns=@JoinColumn(name="Address_Id"))*/
	
	/*3.Using a common join table:*/
	@PrimaryKeyJoinColumn
	Address address;
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int rollNo, String studName, Address address) {
		super();
		RollNo = rollNo;
		this.studName = studName;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [RollNo=" + RollNo + ", studName=" + studName + ", address=" + address + "]";
	}
	public int getRollNo() {
		return RollNo;
	}
	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}


@Entity
@Table(name="OTO_Address")
class Address{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="Address_Id")
	int addressId;
	@Column(name="City")
	private String city;
	@Column(name="Pincode")
	private int pincode;

	public Address(int addressId, String city, int pincode) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.pincode = pincode;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", pincode=" + pincode + "]";
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
}