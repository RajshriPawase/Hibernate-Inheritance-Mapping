package com.HibernateMapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class OneToOneBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Address1 ad=new Address1(1, "pune", 411052, null);
		Student1 st=new Student1(101, "Anu", null);
		Address1 ad1=new Address1(2, "Sangamner", 422605, null);
		Student1 st1=new Student1(102, "Vidya", null);
		/*st.setAddress(ad);
		ad.setStud(st);
		session.save(st);
		st1.setAddress(ad1);
		ad1.setStud(st1);
		session.save(st1);*/
		st.setAddress(ad);
		ad.setStud(st);
		st.setRollNo(ad.getAddressId());
		session.save(st);
		st1.setAddress(ad1);
		ad1.setStud(st1);
		st1.setRollNo(ad1.getAddressId());
		session.save(st1);
		trans.commit();
		session.close();
		System.out.println("Complete successfully");
	}

}

@Entity
@Table(name="OTOBi_Student")
class Student1{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="Roll_No")
	private int RollNo;
	@Column(name="Student_Name")
	private String studName;
	
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="Address_Key")
	//@JoinTable(name="OTOBi_Join",joinColumns=@JoinColumn(name="Roll_No"),inverseJoinColumns=@JoinColumn(name="Address_Id"))
	@PrimaryKeyJoinColumn
	Address1 address;
	
	
	
	public Student1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student1(int rollNo, String studName, Address1 address) {
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
	public Address1 getAddress() {
		return address;
	}
	public void setAddress(Address1 address) {
		this.address = address;
	}
	
	
}


@Entity
@Table(name="OTOBi_Address")
class Address1{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="Address_Id")
	int addressId;
	@Column(name="City")
	private String city;
	@Column(name="Pincode")
	private int pincode;
	
	@OneToOne(mappedBy="address")
	//@JoinColumn(name="Student_Key")
	Student1 stud;


	public Address1(int addressId, String city, int pincode, Student1 stud) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.pincode = pincode;
		this.stud = stud;
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
	public Student1 getStud() {
		return stud;
	}
	public void setStud(Student1 stud) {
		this.stud = stud;
	}
	
	
}