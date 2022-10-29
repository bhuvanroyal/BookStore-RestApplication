	package com.virtusa.bstore.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.bstore.dao.UserRepositoryImpl;
import com.virtusa.bstore.dto.Cart;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.exception.UserExistException;
import com.virtusa.bstore.exception.UserNotFoundException;

@Service
public class IUserServiceImpl implements IUserService{
	
	@Autowired
	UserRepositoryImpl userRepository;
	
	@Autowired
	Environment env;
	
	Log log=LogFactory.getLog(IUserServiceImpl.class);
	
	public String Login(String email,String pwd) {
		if(userRepository.getUser(email, pwd)) {
			log.info(env.getProperty("login.success")+" "+email);
			return env.getProperty("login.success");
		}
		log.info(env.getProperty("login.error"));
		return env.getProperty("login.error");
	}
	
	public String Signup(User u) throws UserExistException {
		if(!userRepository.ifUserExist(u.getUserEmail())) {
			Cart c=new Cart();
			c.setCartTotal(0);
			u.setCart(c);
			userRepository.save(u);
			log.info(env.getProperty("signup.success")+u.getUserEmail());
			return env.getProperty("signup.success");
		}
		throw new UserExistException(env.getProperty("user.exist"));
	}

	public User modifyUser(User u) throws UserNotFoundException {
		if(userRepository.existsById(u.getUserId())) {
			User existingUser=userRepository.findById(u.getUserId()).get();
			existingUser.setDob(u.getDob());
			existingUser.setUserAge(u.getUserAge());
			existingUser.setUserEmail(u.getUserEmail());
			existingUser.setUserLocation(u.getUserLocation());
			existingUser.setUserMobileNo(u.getUserMobileNo());
			existingUser.setUserPassWord(u.getUserPassWord());
			userRepository.save(existingUser);
			log.info(env.getProperty("user.modified"));
			return existingUser;
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
	}
	
	public List<User> getAllUser() {
		log.info(env.getProperty("user.all"));
		return userRepository.findAll();	
	}
	
	public String removeUser(int uid) throws UserNotFoundException {
		if(userRepository.existsById(uid)) {
			userRepository.deleteById(uid);
			log.info(env.getProperty("user.removed"));
			return env.getProperty("user.removed");
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
	}
	public User getUserProfile(int uid) throws UserNotFoundException {
		if(userRepository.existsById(uid)) {
			log.info(env.getProperty("user.profile"));
			return userRepository.findById(uid).get();
		}
		throw new UserNotFoundException(env.getProperty("user.notFound"));
		
	}
}
