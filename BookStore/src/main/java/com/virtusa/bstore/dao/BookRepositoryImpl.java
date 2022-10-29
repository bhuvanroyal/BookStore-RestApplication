package com.virtusa.bstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.bstore.dto.Book;



@Repository
public interface BookRepositoryImpl extends JpaRepository<Book,Integer>{
	
	@Query("select b from Book b where b.bookName like concat(:p1,'%')")
	public List<Book> getBooksByBookName(@Param("p1") String bname);
	
	@Query("select b from Book b where b.bookCategory like concat(:p1,'%')")
	public List<Book> getBooksByCategory(@Param("p1") String category);

}
