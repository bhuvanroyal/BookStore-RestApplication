package com.virtusa.bstore.service;

import java.util.List;

import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.exception.UserExistException;
import com.virtusa.bstore.exception.UserNotFoundException;

public interface IUserService {
	public String Login(String email,String pwd);
	
	public String Signup(User u) throws UserExistException;
	
	public User modifyUser(User u) throws UserNotFoundException;
	
	public List<User> getAllUser();
	
	public String removeUser(int uid) throws UserNotFoundException;
	
	public User getUserProfile(int uid) throws UserNotFoundException;
}
