package com.virtusa.bstore.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="order_table")
public class Order {
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "order_generator")
	@TableGenerator(name="order_generator", table="id_generator",allocationSize = 1)
	private int ordId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ordDate;
	
	private String ordStatus;
	
	private int totalAmount;
	
	@ManyToOne
	User user;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	List<OrderItem> ordItems=new ArrayList<OrderItem>();

	public Order() {
		super();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order(int ordId, Date ordDate, String ordStatus, List<OrderItem> ordItems) {
		super();
		this.ordId = ordId;
		this.ordDate = ordDate;
		this.ordStatus = ordStatus;
		this.ordItems = ordItems;
	}

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public List<OrderItem> getOrdItems() {
		return ordItems;
	}

	public void setOrdItems(List<OrderItem> ordItems) {
		this.ordItems = ordItems;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [ordId=" + ordId + ", ordDate=" + ordDate + ", ordStatus=" + ordStatus + ", totalAmount="
				+ totalAmount + ", user=" + user + ", ordItems=" + ordItems + "]";
	}

	
	

}
