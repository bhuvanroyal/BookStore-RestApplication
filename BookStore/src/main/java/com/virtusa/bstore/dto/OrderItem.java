package com.virtusa.bstore.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "orderItem_generator")
	@TableGenerator(name="orderItem_generator", table="id_generator",allocationSize = 1)
	private int ordItemId;
	
	private int quantity;
	
	@ManyToOne
	Book b;
	
	@JsonIgnore
	@ManyToOne
	Order order;

	public OrderItem() {
		super();
	}

	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	
	public int getOrdItemId() {
		return ordItemId;
	}

	public void setOrdItemId(int ordItemId) {
		this.ordItemId = ordItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [ordItemId=" + ordItemId + ", quantity=" + quantity + ", order=" + order + "]";
	}
	
}
