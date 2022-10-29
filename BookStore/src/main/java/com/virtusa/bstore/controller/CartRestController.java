package com.virtusa.bstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.ICartServiceImpl;

@RestController
@RequestMapping("/cartRest")
public class CartRestController {
	
	@Autowired
	ICartServiceImpl cartService;
	
	Cart c;
	String msg;

	//***Adding book to the UserCart***	
		@PostMapping("/addTocart/{uid}/{pid}")
		public ResponseEntity<?> addcart(@PathVariable("uid") int uid,@PathVariable("pid") int pid) throws UserNotFoundException, BookNotFoundException {
			Cart c=cartService.addcart(uid, pid);
			return new ResponseEntity<Cart>(c, HttpStatus.CREATED);
		}
		
	//***View CartItems By User Id***
		@GetMapping("/viewCart/{uid}")
		public ResponseEntity<?> getAllCartItems(@PathVariable("uid") int uid) throws UserNotFoundException{
			c= cartService.viewAllCartItems(uid);
			return new ResponseEntity<Cart>(c,HttpStatus.FOUND);
		}
		
	//***Deleting cartItem from the Cart Table***	
		@DeleteMapping("/deleteItem/{uid}/{cartItmd}")
		public ResponseEntity<?> removeCartItem(@PathVariable("uid") int uid,@PathVariable("cartItmd") int cid) throws UserNotFoundException, CartItemNotFoundException {
			String msg= cartService.removeCartItem(uid, cid);
			return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
		}
	//	***	Changing quantiy of Cart Item ***
		@PostMapping("/changeQuantity/{itemId}/{quantity}")
		public ResponseEntity<?> changeCartItemQuantity(@PathVariable("itemId") int itemId,@PathVariable("quantity") int quantity) throws CartItemNotFoundException{
			String msg=cartService.changeQuantityOfCartItem(itemId, quantity);
			return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
		}

}
