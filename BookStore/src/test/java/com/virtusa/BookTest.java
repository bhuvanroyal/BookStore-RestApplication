package com.virtusa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.service.IBookServiceImpl;

@SpringBootTest
public class BookTest {
	@Autowired
	IBookServiceImpl bookTest;
	
	@Autowired
	Environment env;
	
	
//	@Test
//	public void addBookTest() {
//		Book b=new Book();
//		b.setBookName("testBook");
//		b.setBookPrice(300);
//		b.setQuantity(40);
//		assertEquals(env.getProperty("book.added"),bookTest.addBook(b));
//	}
	
	@Test
	public void getAllBooksTest() {
		assertEquals(4,bookTest.getAllBooks().size());
	}
	
	@Test
	public void getBookByIdTest() throws BookNotFoundException {
		assertNotNull(bookTest.getBookById(105));
		
		assertThrows(BookNotFoundException.class,()-> bookTest.getBookById(159));
	}
	
//	@Test
//	public void DeleteBookTest() throws BookNotFoundException {
//		assertEquals(env.getProperty("book.deleted"),bookTest.deleteBook(106));
//	}
	
	@Test
	@Disabled
	public void SearchBooksByNameTest() {
		
		assertEquals(9,bookTest.searchBooksByName("bahu").size());
	}

	@Test
	public void searchBookByCategoryTest() {
		assertEquals(0,bookTest.searchBooksByCategory("motivation").size());
	}
}
