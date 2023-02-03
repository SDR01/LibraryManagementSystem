package com.library.controller;

import java.time.LocalDate;
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

import com.library.entity.Borrow;
import com.library.entity.User;
import com.library.exception.BookNotFoundException;
import com.library.exception.BorrowException;
import com.library.exception.UserNotFoundException;
import com.library.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserNotFoundException{
		User newUser = userService.addUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> removeUser(@PathVariable ("id") Integer userId) throws UserNotFoundException{
		User deleteUser = userService.deleteUser(userId);
		return new ResponseEntity<User>(deleteUser, HttpStatus.OK);
	}
	
	@PutMapping("/borrow/{userId}/{bookId}")
	public ResponseEntity<LocalDate> borrowBook(@PathVariable ("userId") Integer userId, @PathVariable ("bookId") Integer bookId) throws BookNotFoundException, BorrowException, UserNotFoundException{
		LocalDate borrowingBook = userService.borrowBook(userId, bookId);
		return new ResponseEntity<LocalDate>(borrowingBook, HttpStatus.OK);
	}
	
	@PutMapping("/return/{userId}/{bookId}/{borrowId}")
	public ResponseEntity<Integer> returnBook(@PathVariable ("userId") Integer userId, @PathVariable ("bookId") Integer bookId, @PathVariable ("borrowId") Integer borrowId) throws BookNotFoundException, BorrowException, UserNotFoundException{
		Integer returningBook = userService.returnBook(userId, bookId, borrowId);
		return new ResponseEntity<Integer>(returningBook, HttpStatus.OK);
	}
	
	@GetMapping("/userBorrowedList/{userId}")
	public ResponseEntity<List<Borrow>> userBorrowedBooks(@PathVariable ("userId") Integer userId) throws UserNotFoundException {
		List<Borrow> borrowedBookList = userService.checkUserBorrowList(userId);
		return new ResponseEntity<List<Borrow>>(borrowedBookList, HttpStatus.OK);
	}

}
