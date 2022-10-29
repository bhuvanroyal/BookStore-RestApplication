package com.virtusa.bstore.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User_table")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "user_generator")
	@TableGenerator(name="user_generator", table="id_generator",allocationSize = 1)
	@Column(name="user_id")
	private int userId;
	@Pattern(regexp="^[a-z0-9]+@[a-z]+\\.[a-z]{2,3}$",message="enter the valid details")
	@Column(name="email")
	private String userEmail;
	@Column(name="password")
	private String userPassWord;
	@Pattern(regexp="^[a-zA-Z]+$",message="enter the valid details")
	@Column(name="location")
	private String userLocation;
	@NotNull
	@Column(name="age")
	private int userAge;
	@NotNull
	@Column(name="mobile_No")
	String userMobileNo;
	@Column(name="DateOfBirth")
	@Temporal(TemporalType.DATE)
	Date dob;
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	List<Order> orders=new ArrayList();
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	Cart cart;
	
	
	public User() {
		super();
	}

	public User(int userId, String userEmail, String userPassWord, String userLocation, int userAge,
			String userMobileNo, Date dob) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPassWord = userPassWord;
		this.userLocation = userLocation;
		this.userAge = userAge;
		this.userMobileNo = userMobileNo;
		this.dob = dob;
	}


	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userPassWord=" + userPassWord
				+ ", userLocation=" + userLocation + ", userAge=" + userAge + ", userMobileNo=" + userMobileNo
				+ ", dob=" + dob + "]";
	}
	

}
