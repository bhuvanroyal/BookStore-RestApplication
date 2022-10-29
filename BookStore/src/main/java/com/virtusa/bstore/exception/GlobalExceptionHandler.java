package com.virtusa.bstore.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Log log=LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(BookNotFoundException.class)
	public @ResponseBody ErrorInfo BookErrorException(BookNotFoundException e, HttpServletRequest req) {
		log.error(e.getMessage());
		return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseBody ErrorInfo UserErrorException(UserNotFoundException e, HttpServletRequest req) {
		log.error(e.getMessage());
		return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		
	}
	
	@ExceptionHandler(CartItemNotFoundException.class)
	public @ResponseBody ErrorInfo CartItemErrorException(CartItemNotFoundException e, HttpServletRequest req) {
		log.error(e.getMessage());
		return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		
	}
	
	@ExceptionHandler(UserExistException.class)
	public @ResponseBody ErrorInfo UserExistErrorException(UserExistException e, HttpServletRequest req) {
		log.error(e.getMessage());
		return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public @ResponseBody ErrorInfo OrderErrorException(OrderNotFoundException e, HttpServletRequest req) {
		log.error(e.getMessage());
		return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
		
	}

}
