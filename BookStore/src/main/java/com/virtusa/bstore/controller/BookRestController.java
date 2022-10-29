package com.virtusa.bstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.bstore.dto.Book;
import com.virtusa.bstore.exception.BookNotFoundException;
import com.virtusa.bstore.service.IBookServiceImpl;

@RestController
@RequestMapping("/bookRest")
public class BookRestController {
	
	
	@Autowired
	IBookServiceImpl bookService;
	
	
		
	//***Getting All Books***	
		@GetMapping("/getAllBooks")
		public ResponseEntity<?> getAllBooks(){
			return new ResponseEntity<List>(bookService.getAllBooks(),HttpStatus.FOUND);
		}	
		
	//***Getting Books By Book Id***	
		@GetMapping("/books/{bid}")
		public ResponseEntity<?> getBookById(@PathVariable("bid") int bid) throws BookNotFoundException {
			Book b=bookService.getBookById(bid);
			return new ResponseEntity<Book>(b,HttpStatus.OK);
		}
		
	//***Searching Books By Name***
		@GetMapping("/searchBooksByName/{bname}")
		public ResponseEntity<?> searchBooksByName(@PathVariable("bname")String bname){
			return new ResponseEntity<List<Book>>(bookService.searchBooksByName(bname),HttpStatus.FOUND);
			
		}
		
	//***Searching Books By Category***	
		@GetMapping("/searchBooksByCategory/{category}")
		public ResponseEntity<?> searchBooksByCategory(@PathVariable("category")String category){
			return new ResponseEntity<List<Book>>(bookService.searchBooksByCategory(category),HttpStatus.FOUND);
		}


}
