package com.virtusa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.ICartServiceImpl;

@SpringBootTest
public class CartTest {
	
	@Autowired
	ICartServiceImpl cartService;
	
	@Autowired
	Environment env;
	
	
	@Test
	public void addItemToCartTest() throws UserNotFoundException, BookNotFoundException {
		assertNotNull(cartService.addcart(502, 102));
	}

	@Test
	public void removeCartItemTest() throws UserNotFoundException, CartItemNotFoundException {
		String msg=cartService.removeCartItem(502,104);
		assertEquals(env.getProperty("cartItem.deleted"),msg);
	}
}
