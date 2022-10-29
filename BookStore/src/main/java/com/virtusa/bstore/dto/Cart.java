package com.virtusa.bstore.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Cart {
	
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE,generator = "cart_generator")
	@TableGenerator(name="cart_generator", table="id_generator",allocationSize = 1)
	private int cartId;
	
	private int cartTotal;
	
	@JsonIgnore
	@OneToOne(mappedBy="cart")
	User user;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL)
	List<CartItem> cartItems=new ArrayList<CartItem>();

	public Cart() {
		super();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(int cartTotal) {
		this.cartTotal = cartTotal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartTotal=" + cartTotal + ", user=" + user + ", cartItems=" + cartItems
				+ "]";
	}

}
