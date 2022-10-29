package com.virtusa.bstore.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "cartItem_generator")
	@TableGenerator(name="cartItem_generator", table="id_generator",allocationSize = 1)
	private int cartItemId;
	
	private int quantity;
	
	
	@ManyToOne
	Book b;
	
	@JsonIgnore
	@ManyToOne
	Cart cart;
	

	public CartItem() {
		super();
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public Book getB() {
		return b;
	}

	public void setB(Book b) {
		this.b = b;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", quantity=" + quantity + ", b=" + b + "]";
	}

	
	
	
	

}
