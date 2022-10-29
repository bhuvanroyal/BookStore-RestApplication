package com.virtusa.bstore.service;

import java.util.Date;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.bstore.dao.BookRepositoryImpl;
import com.virtusa.bstore.dao.CartItemRepositoryImpl;
import com.virtusa.bstore.dao.CartRepositoryImpl;
import com.virtusa.bstore.dao.OrderRepositoryImpl;
import com.virtusa.bstore.dao.UserRepositoryImpl;
import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.CartItem;
import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.OrderItem;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.OrderNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;

@Service
public class IOrderServiceImpl implements IOrderService{
	
	@Autowired
	UserRepositoryImpl userRepository;
	
	@Autowired
	BookRepositoryImpl bookRepository;
	
	@Autowired
	CartRepositoryImpl cartRepository;
	
	@Autowired
	OrderRepositoryImpl orderRepository;
	
	@Autowired
	CartItemRepositoryImpl cartItemRepository;
	
	@Autowired
	Environment env;
	
	Log log=LogFactory.getLog(IOrderServiceImpl.class);
	
	public Order buyingBook(int uid,int bid) throws UserNotFoundException, BookNotFoundException {
		if(!userRepository.existsById(uid)) {
			throw new UserNotFoundException(env.getProperty("user.notFound"));
		}
		if(!bookRepository.existsById(bid)) {
			throw new BookNotFoundException(env.getProperty("book.notFound"));
		}
		Order o=new Order();
		OrderItem ot=new OrderItem();
		Book b=bookRepository.findById(bid).get();
		o.setUser(userRepository.findById(uid).get());
		o.setOrdDate(new Date());
		o.setOrdStatus("active");
		o.setTotalAmount(b.getBookPrice());
		ot.setB(bookRepository.findById(bid).get());
		ot.setQuantity(1);
		ot.setOrder(o);
		o.getOrdItems().add(ot);
		orderRepository.save(o);
		b.setQuantity(b.getQuantity()-1);
		bookRepository.save(b);
		log.info(env.getProperty("order.book"));
		return o;	
	}
	
	public Order buyingCartItems(int uid) throws UserNotFoundException, CartItemNotFoundException {
		if(userRepository.existsById(uid)){
			Cart c=userRepository.findById(uid).get().getCart();
			if(!c.getCartItems().isEmpty()) {
				Order o=new Order();
				o.setOrdDate(new Date());
				o.setOrdStatus("active");
				o.setUser(userRepository.findById(uid).get());
				int total=0;
				for(CartItem i:c.getCartItems()) {
					Book b=i.getB();
					total+=i.getQuantity()*b.getBookPrice();
					b.setQuantity(b.getQuantity()-i.getQuantity());
					OrderItem ot=new OrderItem();
					ot.setB(i.getB());
					ot.setQuantity(i.getQuantity());
					ot.setOrder(o);
					o.getOrdItems().add(ot);
					bookRepository.save(b);
				}
				cartItemRepository.deleteAllInBatch(c.getCartItems());
				c.setCartTotal(0);
				cartRepository.save(c);
				o.setTotalAmount(total);
				orderRepository.save(o);
				log.info(env.getProperty("order.cartItems"));
				return o;
			}
			throw new CartItemNotFoundException(env.getProperty("cartItem.items"));
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
		
	}
	
	public List<Order> getAllActiveOrders(){
		log.info(env.getProperty("order.allActive"));
		return orderRepository.getAllOrdersByStatus("active");
	}
	
	public List<Order> getAllDeliveredOrders(){
		log.info(env.getProperty("order.allDelivered"));
		return orderRepository.getAllOrdersByStatus("delivered");
	}
	
	public List<Order> getActiveOrders(int uid) throws UserNotFoundException{
		if(userRepository.existsById(uid)){
			User u=userRepository.findById(uid).get();
			log.info(env.getProperty("order.activeOrders")+u.getUserEmail());
			return orderRepository.getOrdersByStatus(u,"active");
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
	}
	
	public List<Order> getDeliveredOrders(int uid) throws UserNotFoundException{
		if(userRepository.existsById(uid)){
			User u=userRepository.findById(uid).get();
			log.info(env.getProperty("order.deliveredOrders")+u.getUserEmail());
			return orderRepository.getOrdersByStatus(u,"delivered");
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
	}

	public Order updateOrderStatus(int ordId,String status) throws OrderNotFoundException {
		if(orderRepository.existsById(ordId)) {
			Order o=orderRepository.findById(ordId).get();
			o.setOrdStatus(status);
			orderRepository.save(o);
			log.info(env.getProperty("order.status")+ordId);
			return o;
		}
		throw new OrderNotFoundException(env.getProperty("order.notFound"));
	}
	
	public Order getOrderItems(int ordId) throws OrderNotFoundException{
		if(orderRepository.existsById(ordId)) {
			Order o=orderRepository.findById(ordId).get();
			log.info(env.getProperty("order.item")+ordId);
			return o;
		}
		throw new OrderNotFoundException(env.getProperty("order.notFound"));	
	}
	
	
}
