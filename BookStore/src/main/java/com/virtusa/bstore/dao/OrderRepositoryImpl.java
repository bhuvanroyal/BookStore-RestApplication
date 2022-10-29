package com.virtusa.bstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.Order;
import com.virtusa.bstore.dto.User;

@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order,Integer>{
	
	@Query("select o from Order o where o.user=:p1 and o.ordStatus=:p2")
	public List<Order> getOrdersByStatus(@Param("p1") User u,@Param("p2") String status);
	
	@Query("select o from Order o where o.ordStatus=:p1")
	public List<Order> getAllOrdersByStatus(@Param("p1") String status);
}
