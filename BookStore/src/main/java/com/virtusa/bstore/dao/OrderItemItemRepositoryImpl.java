package com.virtusa.bstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.OrderItem;

@Repository
public interface OrderItemItemRepositoryImpl extends JpaRepository<OrderItem,Integer>{

}
