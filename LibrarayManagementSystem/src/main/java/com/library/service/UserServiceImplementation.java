package com.library.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.entity.User;
import com.library.exception.BookNotFoundException;
import com.library.exception.BorrowException;
import com.library.exception.UserNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;

	@Override
	public User addUser(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public User deleteUser(Integer userId) throws UserNotFoundException{
		
		Optional<User> validateUser = userRepository.findById(userId);
		
		if(validateUser.isPresent()) {
			User currentUser = validateUser.get();
			userRepository.delete(currentUser);
			
			return currentUser;
		}
		else throw new UserNotFoundException("Invalid User ID") ;
	}

	@Override
	public LocalDate borrowBook(Integer userId, Integer bookId) throws BookNotFoundException,BorrowException,UserNotFoundException {
		
		LocalDate dueDate = null;
		
		Optional<User> validateUser = userRepository.findById(userId);
		
		if(validateUser.isPresent()) {
			
			Optional<Book> validateBook = bookRepository.findById(bookId);
			
			if(validateBook.isPresent()) {
				
				Boolean flag = validateBook.get().isIssued();
				
				if(flag) throw new BookNotFoundException("Book already issued to another User.");
				
				else {
					int userBorrowedBooks = validateUser.get().getBorrowedBooks().size();
					
					if(userBorrowedBooks >= 5) {
						throw new BorrowException("Borrowing limit exceeded.");
					}
					
					else {
						Book borrowingBook = validateBook.get();
						borrowingBook.setIssued(true);
						bookRepository.save(borrowingBook);
						
						Borrow newBorrowedBook = new Borrow();
						newBorrowedBook.setBookId(bookId);
						newBorrowedBook.setIssueDate(LocalDate.now());
						newBorrowedBook.setDueDate(LocalDate.now().plusWeeks(1));
						
						dueDate = newBorrowedBook.getDueDate();
						
						Borrow borrowedBook = borrowRepository.save(newBorrowedBook);
						
						User userUpdate = validateUser.get();
						userUpdate.getBorrowedBooks().add(borrowedBook);
						userRepository.save(userUpdate);
					}
				}
			}
			else throw new BookNotFoundException("Invalid Book ID");
		}
		else throw new UserNotFoundException("Invalid User ID");
		return dueDate;
	}

	@Override
	public Integer returnBook(Integer userId, Integer bookId, Integer borrowId) throws UserNotFoundException, BorrowException {
		
		int fine = 0;
		
		Optional<User> validateUser = userRepository.findById(userId);
		Optional<Book> validateBook = bookRepository.findById(bookId);
		Optional<Borrow> validateBorrow = borrowRepository.findById(borrowId);
		
		if(validateUser.isPresent()) {
			
			User currentUser = validateUser.get();
			Boolean userBorrowedBook = currentUser.getBorrowedBooks().contains(validateBorrow.get());
			
			if(userBorrowedBook) {
				
				if(validateBorrow.isPresent()) {
					
					Borrow borrow = validateBorrow.get();
					int days = Period.between(borrow.getIssueDate(), borrow.getDueDate()).getDays();
					
					if(days < 0) {
						fine = Math.abs(days) * 10;
					}
					else {
						
						currentUser.getBorrowedBooks().remove(validateBorrow.get());
						userRepository.save(currentUser);
						
						Book returningBorrowedBook = validateBook.get();
						returningBorrowedBook.setIssued(false);
						bookRepository.save(returningBorrowedBook);
						
					}
					
				}
				else throw new BorrowException("Invalid Borrow ID");
			}
			else throw new BorrowException("Invalid Borrow ID");
		}
		else throw new UserNotFoundException("Invalid User ID");
		return fine;
	}

	@Override
	public List<Borrow> checkUserBorrowList(Integer userId) throws UserNotFoundException {
		
		Optional<User> validateUser = userRepository.findById(userId);
		
		if(validateUser.isPresent()) {
			User currentUser = validateUser.get();
			List<Borrow> borrowList = currentUser.getBorrowedBooks();
			return borrowList;
		}
		else throw new UserNotFoundException("Invalid User ID") ;
	}

}
