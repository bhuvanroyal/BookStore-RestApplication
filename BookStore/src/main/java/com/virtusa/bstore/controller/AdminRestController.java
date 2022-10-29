package com.virtusa.bstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.OrderNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.IBookServiceImpl;
import com.virtusa.bstore.service.IOrderServiceImpl;
import com.virtusa.bstore.service.IUserServiceImpl;



@RestController
@RequestMapping("/adminRest")
public class AdminRestController {
	
	
	@Autowired
	IBookServiceImpl bookService;
	
	@Autowired
	IOrderServiceImpl orderService;
	
	@Autowired
	IUserServiceImpl userService;

	String msg;
	
	
//***Modifying Book***	
	@PutMapping("/modifyBook")
	public ResponseEntity<?> modifyBook(@RequestBody Book b) throws BookNotFoundException {
		b= bookService.modifyBook(b);
		return new ResponseEntity<Book>(b,HttpStatus.OK);
	}
			
//*** Deleting Book By ***
	@DeleteMapping("/deleteBook/{bid}")
	public ResponseEntity<?> deleteBook(@PathVariable("bid") int bid) throws BookNotFoundException{
		 msg=bookService.deleteBook(bid);
	   	 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
			
//***Adding new Book***	
	@PostMapping("/addBook")
	public  ResponseEntity<?> addingBook(@RequestBody Book b) {
		msg=bookService.addBook(b);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
		
			
//***Getting All Active Orders***	
	@GetMapping("/activeOrders")
	public List<Order> getAllActiveOrders(){
		 return orderService.getAllActiveOrders();
	}

//***Getting All Delivered Orders***
	@GetMapping("/deliveredOrders")
	public List<Order> getAllDeliveredOrders(){
   		 return orderService.getAllDeliveredOrders();
	}
//***updating orderStatus to Delivered***	
	@PostMapping("/updateOrderStatus/{ordId}/{status}")
	public Order updatingOrderStatus(@PathVariable("ordId") int ordId,@PathVariable("status") String status) throws OrderNotFoundException {
		 return orderService.updateOrderStatus(ordId,status);
	}
	
	//***Getting OrderItems by Order Id***	
		@GetMapping("/orders/{ordId}")
		public Order getOrderItems(@PathVariable("ordId") int ordId) throws OrderNotFoundException{
			return orderService.getOrderItems(ordId);
		}
			
			
	//***Getting Active Orders By userId***	
		@GetMapping("/activeOrders/{uid}")
		public List<Order> getActiveOrders(@PathVariable("uid") int uid) throws UserNotFoundException{
			return orderService.getActiveOrders(uid);
		}
				
	//***Getting Delivered Orders By userID***
		@GetMapping("/deliveredOrders/{uid}")
		public List<Order> getDeliveredOrders(@PathVariable("uid") int uid) throws UserNotFoundException{
			return orderService.getDeliveredOrders(uid);
		}
	
					
//***Getting All USers***	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUser() {	
		return new ResponseEntity<List>(userService.getAllUser(),HttpStatus.FOUND);
	}
			
//***Removing USer***	
	@DeleteMapping("/deleteUser/{uid}")
	public ResponseEntity<?> removeUser(@PathVariable("uid") int uid) throws UserNotFoundException {
		 msg= userService.removeUser(uid);
		 return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	@GetMapping("/userProfile/{uid}")
	public ResponseEntity<?> getUserProfile(@PathVariable("uid")int uid) throws UserNotFoundException {
		User u= userService.getUserProfile(uid);
		return new ResponseEntity<User>(u, HttpStatus.FOUND);
	}
}
