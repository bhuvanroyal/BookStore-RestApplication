package com.virtusa.bstore.service;

import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;

public interface ICartService {

	public Cart addcart(int uid,int pid) throws UserNotFoundException, BookNotFoundException;
	
	public String removeCartItem(int uid, int cartItmd) throws UserNotFoundException, CartItemNotFoundException;
	
	public Cart viewAllCartItems(int uid) throws UserNotFoundException;
	
	public String changeQuantityOfCartItem(int itemId,int quantity)throws CartItemNotFoundException;
}
