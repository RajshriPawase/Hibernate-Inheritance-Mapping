package com.HibernateMapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateDemo.HibernateUtil;
import com.HibernateDemo.MyException;

public class ManyToManyBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();

		Book1 b1=new Book1(101, "Hibernate", 200, null);
		Book1 b2=new Book1(102, "SQL", 100, null);
		
		Author1 a1=new Author1(1, "Rajshri", null);
		Author1 a2=new Author1(2, "Varsha", null);
		Author1 a3=new Author1(3, "Sachin", null);
		
		List<Author1> li1=new ArrayList<Author1>();
		li1.add(a1);
		li1.add(a2);
		li1.add(a3);
				
		List<Author1> li2=new ArrayList<Author1>();
		li2.add(a1);
		li2.add(a2);
		
		b1.setAuthor(li1);
		b2.setAuthor(li2);
		
		session.save(b1);
		session.save(b2);
		trans.commit();
		session.close();
		System.out.println("Complete Successfully");
	}
}

@Entity
@Table(name="MTMBi_Book")
class Book1{
	@Id
	@Column(name="Book_Id")
	private int book_Id;
	@Column(name="Book_Name")
	private String book_Name;
	@Column(name="Price")
	private int price;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Bk_auth_Bi",joinColumns=@JoinColumn(name="Book_Id"),inverseJoinColumns=@JoinColumn(name="Author_Id"))
	List<Author1> author;
	public Book1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book1(int book_Id, String book_Name, int price, List<Author1> author) {
		super();
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.price = price;
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [book_Id=" + book_Id + ", book_Name=" + book_Name + ", price=" + price + ", author=" + author
				+ "]";
	}
	public int getBook_Id() {
		return book_Id;
	}
	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}
	public String getBook_Name() {
		return book_Name;
	}
	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Author1> getAuthor() {
		return author;
	}
	public void setAuthor(List<Author1> author) {
		this.author = author;
	}
	
	
}

@Entity
@Table(name="MTMBi_Author")
class Author1{
	@Id
	@Column(name="Author_Id")
	private int author_Id;
	@Column(name="Author_Name")
	private String author_Name;
	@ManyToMany(mappedBy="author")
	List<Book1> book;
	public Author1(int author_Id, String author_Name, List<Book1> book) {
		super();
		this.author_Id = author_Id;
		this.author_Name = author_Name;
		this.book = book;
	}
	@Override
	public String toString() {
		return "Author1 [author_Id=" + author_Id + ", author_Name=" + author_Name + ", book=" + book + "]";
	}
	public int getAuthor_Id() {
		return author_Id;
	}
	public void setAuthor_Id(int author_Id) {
		this.author_Id = author_Id;
	}
	public String getAuthor_Name() {
		return author_Name;
	}
	public void setAuthor_Name(String author_Name) {
		this.author_Name = author_Name;
	}
	public List<Book1> getBook() {
		return book;
	}
	public void setBook(List<Book1> book) {
		this.book = book;
	}
	
	
}