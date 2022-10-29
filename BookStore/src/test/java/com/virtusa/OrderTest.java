package com.virtusa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.IOrderServiceImpl;

@SpringBootTest
public class OrderTest {
	
	@Autowired
	IOrderServiceImpl orderTest;
	
	@Test
	public void getActiveOrdersByUser() throws UserNotFoundException {
		assertEquals(4,orderTest.getActiveOrders(501).size());
	}
	
	@Test
	public void getDeliveredOrdersByUSer() throws UserNotFoundException {
		assertEquals(1,orderTest.getDeliveredOrders(501).size());
	}
}
