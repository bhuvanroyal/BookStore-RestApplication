package com.virtusa.bstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User,Integer> {
	
	 @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM User u where u.userEmail=:p1 and u.userPassWord=:p2")
	 public boolean getUser(@Param("p1") String email, @Param("p2") String pwd);
	 
	 @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM User u where u.userEmail=:p1")
	 public boolean ifUserExist(@Param("p1") String email);

}
