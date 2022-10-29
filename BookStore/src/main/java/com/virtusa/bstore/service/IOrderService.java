package com.virtusa.bstore.service;

import java.util.List;

import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.OrderNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;

public interface IOrderService {
	
	public Order buyingBook(int uid,int bid) throws UserNotFoundException, BookNotFoundException;
	
	public Order buyingCartItems(int uid) throws UserNotFoundException, CartItemNotFoundException;
	
	public List<Order> getAllActiveOrders();
	
	public List<Order> getAllDeliveredOrders();
	
	public List<Order> getActiveOrders(int uid) throws UserNotFoundException;
	
	public List<Order> getDeliveredOrders(int uid) throws UserNotFoundException;
	
	public Order updateOrderStatus(int ordId,String status) throws OrderNotFoundException;
	
	public Order getOrderItems(int ordId) throws OrderNotFoundException;

}
