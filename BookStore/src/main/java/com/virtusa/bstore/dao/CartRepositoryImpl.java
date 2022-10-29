package com.virtusa.bstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.Cart;
@Repository
public interface CartRepositoryImpl extends JpaRepository<Cart,Integer>{

}
