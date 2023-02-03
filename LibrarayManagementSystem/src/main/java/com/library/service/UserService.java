package com.library.service;

import java.time.LocalDate;
import java.util.List;

import com.library.entity.Borrow;
import com.library.entity.User;
import com.library.exception.BookNotFoundException;
import com.library.exception.BorrowException;
import com.library.exception.UserNotFoundException;

public interface UserService {
	
	public User addUser(User user);
	
	public User deleteUser(Integer userId) throws UserNotFoundException;
	
	public LocalDate borrowBook(Integer userId, Integer bookId) throws BookNotFoundException, BorrowException, UserNotFoundException;
	
	public Integer returnBook(Integer usreId, Integer bookId, Integer borrowId) throws UserNotFoundException, BorrowException;

	public List<Borrow> checkUserBorrowList(Integer userId) throws UserNotFoundException;
}
