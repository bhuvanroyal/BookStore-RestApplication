package com.virtusa.bstore.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="book_table")
public class Book {
	
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "book_generator")
	@TableGenerator(name="book_generator", table="id_generator",allocationSize = 1)
	@Column(name="book_Id")
	private int bookId;
	@Column(name="Name")
	private String bookName;
	@Column(name="Author")
	private String bookAuthor;
	@Column(name="Category")
	private String bookCategory;
	@Column(name="quantity")
	private int quantity;
	@Column(name="price")
	int bookPrice;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="b")
	List<CartItem> cartItems;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="b")
	List<OrderItem> orderItems;
//	@ManyToMany(mappedBy="ordItems",cascade=CascadeType.ALL)
//	List<Order> orders=new ArrayList<Order>();
	
//	@ManyToMany(mappedBy="cartItems",cascade=CascadeType.ALL)
//	List<Cart> carts=new ArrayList<Cart>();
	public Book() {
		super();
	}

	public Book(int bookId, String bookName, String bookAuthor, String bookCategory, int quantity, int bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.quantity = quantity;
		this.bookPrice = bookPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookCategory="
				+ bookCategory + ", quantity=" + quantity + ", bookPrice=" + bookPrice + "]";
	}
	
}
