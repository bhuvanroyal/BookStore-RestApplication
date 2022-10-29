package com.virtusa.bstore.service;

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
import com.virtusa.bstore.dao.UserRepositoryImpl;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.CartItem;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.exception.CartItemNotFoundException;
import com.virtusa.bstore.exception.UserNotFoundException;

@Service
public class ICartServiceImpl implements ICartService{
	@Autowired
	Environment env;
	
	@Autowired
	CartRepositoryImpl cartRepository;
	
	@Autowired
	UserRepositoryImpl userRepository;
	
	@Autowired
	BookRepositoryImpl bookRepository;
	
	@Autowired
	CartItemRepositoryImpl cartItemRepository;
	
	Log log=LogFactory.getLog(ICartServiceImpl.class);
	
	public Cart addcart(int uid,int pid) throws UserNotFoundException, BookNotFoundException {
		if(!userRepository.existsById(uid)) {
			throw new UserNotFoundException(env.getProperty("user.notFound"));
		}
		Cart c=userRepository.findById(uid).get().getCart();
		if(!bookRepository.existsById(pid)) {
			throw new BookNotFoundException(env.getProperty("book.notFound"));
		}
		c.setCartTotal(c.getCartTotal()+bookRepository.findById(pid).get().getBookPrice());
		for(CartItem i:c.getCartItems()) {
			if(i.getB().getBookId()==pid ) {
				i.setQuantity(i.getQuantity()+1);
				cartRepository.save(c);
				log.info(env.getProperty("cartItem.quantity"));
				return c;
			}	
		}
		CartItem ca=new CartItem();
		ca.setB(bookRepository.findById(pid).get());
		ca.setQuantity(1);
		ca.setCart(c);
		c.getCartItems().add(ca);
		cartRepository.save(c);
		log.info(env.getProperty("cartItem.added"));
		return c;
		
	}
	
	public String removeCartItem(int uid, int cartItmd) throws UserNotFoundException, CartItemNotFoundException{
		if(!userRepository.existsById(uid)) {
			throw new UserNotFoundException(env.getProperty("user.notFound"));
		}
		if(!cartItemRepository.existsById(cartItmd)) {
			throw new CartItemNotFoundException(env.getProperty("cartItem.notFound"));
		}
		Cart c=userRepository.findById(uid).get().getCart();
		int total=cartItemRepository.findById(cartItmd).get().getQuantity()*cartItemRepository.findById(cartItmd).get().getB().getBookPrice();
		c.setCartTotal(c.getCartTotal()-total);
		cartRepository.save(c);
		cartItemRepository.deleteById(cartItmd);
		log.info(env.getProperty("cartItem.deleted"));
		return env.getProperty("cartItem.deleted");
	}
	
	public Cart viewAllCartItems(int uid) throws UserNotFoundException{
		if(userRepository.existsById(uid)) {
		Cart c=userRepository.findById(uid).get().getCart();
		int total=0;
		for(CartItem i:c.getCartItems()) {
			total+=i.getB().getBookPrice()*i.getQuantity();
		}
		c.setCartTotal(total);
		cartRepository.save(c);
		log.info(env.getProperty("cart.viewed"));
		return c;
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
	}
	
	public String changeQuantityOfCartItem(int itemId,int quantity)throws CartItemNotFoundException{
			if(cartItemRepository.existsById(itemId)) {
				CartItem ci=cartItemRepository.findById(itemId).get();
				ci.setQuantity(quantity);
				cartItemRepository.save(ci);
				log.info(env.getProperty("cartItem.quantity")+quantity);
				return env.getProperty("cartItem.quantity")+quantity;
			}
			throw new CartItemNotFoundException(env.getProperty("cartItem.notFound"));
		
	}
	

}
