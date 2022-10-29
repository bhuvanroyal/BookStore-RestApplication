 package com.virtusa.bstore.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtusa.bstore.dto.User;
import com.virtusa.bstore.exception.UserExistException;
import com.virtusa.bstore.exception.UserNotFoundException;
import com.virtusa.bstore.service.IUserServiceImpl;

@RestController
@RequestMapping("/userRest")
public class UserRestController {
	
	
	@Autowired
	IUserServiceImpl userService;
	
	@Autowired
	Environment env;
	
	String msg;

	
//***User Login Validation***
	@GetMapping("/login/{email}/{pwd}")
	public ResponseEntity<?> Login(@PathVariable("email")String email,@PathVariable("pwd")String pwd) {
		msg=userService.Login(email, pwd);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
//***User Signup ***	
	@PostMapping("/signup")
	public ResponseEntity<?> addingUser(@RequestBody @Valid User u,BindingResult br) throws UserExistException {
		if(br.hasErrors()) {
			return new ResponseEntity<String>(env.getProperty("validation.error"), HttpStatus.BAD_REQUEST);
		}
		msg=userService.Signup(u);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
//***Modifying User Details***
	@PostMapping("/modifyUser")
	public ResponseEntity<?> modifyUser(@RequestBody @Valid User u,BindingResult br) throws UserNotFoundException {
		if(br.hasErrors()) {
			return new ResponseEntity<String>(env.getProperty("validation.error"), HttpStatus.BAD_REQUEST);
		}
		User u1=userService.modifyUser(u);
		return new ResponseEntity<User>(u1, HttpStatus.OK);
	}
//***Getting user Profile By User ID***	
	@GetMapping("/userProfile/{uid}")
	public ResponseEntity<?> getUserProfile(@PathVariable("uid")int uid) throws UserNotFoundException {
		User u= userService.getUserProfile(uid);
		return new ResponseEntity<User>(u, HttpStatus.FOUND);
	}
	
	

		
	
}
