package com.virtusa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.IUserServiceImpl;

@SpringBootTest
public class UserTest {
	@Autowired
	IUserServiceImpl userTest;
	
	@Autowired
	Environment env;

	@Test
	public void findUserTest() throws UserNotFoundException{
		assertNotNull(userTest.getUserProfile(502));
		
		assertThrows(UserNotFoundException.class, ()->userTest.getUserProfile(509));
	}
	
	@Test
	public void validateUserTest() {
		String msg=userTest.Login("bhuvan@Gmail.com","dhoni123");
		assertEquals(env.getProperty("login.success"),msg);
		
		String msg2=userTest.Login("bhuvan@Gmail.com","kohli");
		assertEquals(env.getProperty("login.error"),msg2);
	}
	
	@Test
	public void getAllUsersTest() {
		assertEquals(4,userTest.getAllUser().size());
	}
	
//	@Test
//	public void removeUserTest() throws UserNotFoundException {
//		assertEquals(env.getProperty("user.removed"),userTest.removeUser(509));
//		
//		assertThrows(UserNotFoundException.class,()->userTest.removeUser(509));
//		
//	}
//	
//	
//	
//	
	
	
	
}
