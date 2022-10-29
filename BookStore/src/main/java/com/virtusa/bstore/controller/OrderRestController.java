package com.virtusa.bstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.OrderNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.IOrderServiceImpl;

@RestController
@RequestMapping("/orderRest")
public class OrderRestController {
	@Autowired
	IOrderServiceImpl orderService;
	
	
		
//***Getting OrderItems by Order Id***	
	@GetMapping("/orders/{ordId}")
	public  ResponseEntity<?> getOrderItems(@PathVariable("ordId") int ordId) throws OrderNotFoundException{
		return new ResponseEntity<Order>(orderService.getOrderItems(ordId),HttpStatus.FOUND);
	}
		
		
//***Getting Active Orders By userId***	
	@GetMapping("/activeOrders/{uid}")
	public ResponseEntity<?> getActiveOrders(@PathVariable("uid") int uid) throws UserNotFoundException{
		return new ResponseEntity<List<Order>>(orderService.getActiveOrders(uid),HttpStatus.FOUND);
	}
			
//***Getting Delivered Orders By userID***
	@GetMapping("/deliveredOrders/{uid}")
	public ResponseEntity<?> getDeliveredOrders(@PathVariable("uid") int uid) throws UserNotFoundException{
		return new ResponseEntity<List<Order>>(orderService.getDeliveredOrders(uid),HttpStatus.FOUND);
	}
			
//***buying Book Without add to Cart***	
	@PostMapping("/buyBook/{uid}/{bid}")
	public ResponseEntity<?> buyingBook(@PathVariable("uid") int uid,@PathVariable("bid") int bid) throws UserNotFoundException, BookNotFoundException {
		return new ResponseEntity<Order>(orderService.buyingBook(uid, bid),HttpStatus.CREATED);
	}	

//***Buying Cart Items***	
	@PostMapping("/buyCartItems/{uid}")
	public ResponseEntity<?> buyingCartItems(@PathVariable("uid")int uid) throws UserNotFoundException, CartItemNotFoundException {
		return new ResponseEntity<Order>(orderService.buyingCartItems(uid),HttpStatus.CREATED);
	}

	

}
