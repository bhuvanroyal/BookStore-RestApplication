package com.virtusa.bstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.CartItem;

@Repository
public interface CartItemRepositoryImpl extends JpaRepository<CartItem,Integer>{

}
