package com.virtusa.bstore.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.virtusa.bstore.dao.BookRepositoryImpl;
import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.exception.BookNotFoundException;
	
@Service
public class IBookServiceImpl implements IBookService{
	
	@Autowired
	BookRepositoryImpl bookRepository;
	
	@Autowired
	Environment env;
	
	Log log=LogFactory.getLog(IBookServiceImpl.class);
	
	public List<Book> getAllBooks(){
		log.info(env.getProperty("book.viewed"));
		return bookRepository.findAll();
	}
	
	public String addBook(Book b) {
		bookRepository.save(b);
		log.info(env.getProperty("book.added"));
		return env.getProperty("book.added");
	}
	
	public String deleteBook(int bid) throws BookNotFoundException{
		if(bookRepository.existsById(bid)) {
//			Book b=bookRepository.findById(bid).get();
//			b.setQuantity(0);
//			bookRepository.save(b);
			bookRepository.deleteById(bid);
			log.info(env.getProperty("book.deleted"));
			return env.getProperty("book.deleted");
		}
		throw new BookNotFoundException(env.getProperty("book.notFound"));
	}
	
	public Book modifyBook(Book b) throws BookNotFoundException {
		if(bookRepository.existsById(b.getBookId())) {
			Book existingBook=bookRepository.findById(b.getBookId()).get();
			existingBook.setBookAuthor(b.getBookAuthor());
			existingBook.setBookCategory(b.getBookCategory());
			existingBook.setBookName(b.getBookName());
			existingBook.setBookPrice(b.getBookPrice());
			existingBook.setQuantity(b.getQuantity());
			bookRepository.save(b);
			log.info(env.getProperty("book.modified"));
			return existingBook;
		}
		throw new BookNotFoundException(env.getProperty("book.notFound"));
	}
	
	public Book getBookById(int bid) throws BookNotFoundException  {
		if(bookRepository.existsById(bid)) {
			return bookRepository.findById(bid).get();
		}
		throw new BookNotFoundException(env.getProperty("book.notFound"));
	}
	
	public List<Book> searchBooksByName(String bname){
		log.info(env.getProperty("book.name")+bname);
		return bookRepository.getBooksByBookName(bname);
	}
	
	public List<Book> searchBooksByCategory(String category){
		log.info(env.getProperty("book.category")+category);
		return bookRepository.getBooksByCategory(category);
	}

}
