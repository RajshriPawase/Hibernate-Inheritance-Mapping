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

public class ManyToManyUni {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction trans=session.beginTransaction();
		Author a1=new Author(1, "Akshay");
		Author a2=new Author(2, "Varsha");
		Author a3=new Author(3, "Rajshri");
		List<Author> author=new ArrayList<Author>();
		author.add(a1);
		author.add(a2);
		List<Author> author1=new ArrayList<Author>();
		author1.add(a2);
		author1.add(a3);
		Book b1=new Book(101, "Core Java", 500, author);
		Book b2=new Book(102, "Clonning", 800, author1);
		session.save(b1);
		session.save(b2);
		trans.commit();
		session.close();
		System.out.println("Complete Successfully");
	}

}

@Entity
@Table(name="MTMU_Book")
class Book{
	@Id
	@Column(name="Book_Id")
	private int book_Id;
	@Column(name="Book_Name")
	private String book_Name;
	@Column(name="Price")
	private int price;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Bk_auth_Uni",joinColumns=@JoinColumn(name="Book_Id"),inverseJoinColumns=@JoinColumn(name="Author_Id"))
	List<Author> author =new ArrayList<Author>();
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int book_Id, String book_Name, int price, List<Author> author) {
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
	public List<Author> getAuthor() {
		return author;
	}
	public void setAuthor(List<Author> author) {
		this.author = author;
	}
	
}

@Entity
@Table(name="MTMU_Author")
class Author{
	@Id
	@Column(name="Author_Id")
	private int author_Id;
	@Column(name="Author_Name")
	private String author_Name;
	public Author(int author_Id, String author_Name) {
		super();
		this.author_Id = author_Id;
		this.author_Name = author_Name;
	}
	@Override
	public String toString() {
		return "Author [author_Id=" + author_Id + ", author_Name=" + author_Name + "]";
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
	
}