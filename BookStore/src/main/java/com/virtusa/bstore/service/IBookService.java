package com.virtusa.bstore.service;

import java.util.List;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.exception.BookNotFoundException;


public interface IBookService {

	public List<Book> getAllBooks();
	
	public String addBook(Book b);
	
	public String deleteBook(int bid)throws BookNotFoundException;
	
	public Book modifyBook(Book b)throws BookNotFoundException;
	
	public Book getBookById(int bid)throws BookNotFoundException;
	
	public List<Book> searchBooksByName(String bname);
	
	public List<Book> searchBooksByCategory(String category);
}
