package com.library;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.entity.Book;
import com.library.entity.Borrow;
import com.library.entity.User;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.UserRepository;

@SpringBootTest
class LibrarayManagementSystemApplicationTests {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Test
	public void testNewBook() {
		Book newBook = new Book();
		newBook.setBookId(101);
		newBook.setTitle("Learn DSA");
		newBook.setAuthor("Alpha Master");
		newBook.setCategory("Computer Science");
		newBook.setPublisher("Bravo Publisher");
		newBook.setDescription("This book should be read after one has a good hold over basic data structures");
		newBook.setIssued(false);
		bookRepository.save(newBook);
		assertNotNull(bookRepository.findById(101));
	}
	
	@Test
	public void testAllBookList() {
		List<Book> list = bookRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void testBookUpdate() {
		Book book = bookRepository.findById(16).get();
		book.setCategory("Problem Solving");
		bookRepository.save(book);
		assertNotEquals(200.00, bookRepository.findById(16).get().getCategory());
	}
	
	@Test
	public void testDeleteBook() {
		bookRepository.deleteById(15);;
		assertThat(bookRepository.existsById(15)).isFalse();
	}
	
	@Test
	public void testAllAvailableBookList() {
		List<Book> list = bookRepository.findAll();
		List<Book> borrowedBooks = new ArrayList<>();
		for(Book books: list) {
			if(!books.isIssued()) {
				borrowedBooks.add(books);
			}
		}
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void testAllBorrowedBookList() {
		List<Book> list = bookRepository.findAll();
		List<Book> borrowedBooks = new ArrayList<>();
		for(Book books: list) {
			if(books.isIssued()) {
				borrowedBooks.add(books);
			}
		}
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void testNewUser() {
		User newUser = new User();
		newUser.setUserId(22);
		newUser.setName("Charlie");
		newUser.setPhoneNumber(789456130);
		userRepository.save(newUser);
		assertNotNull(userRepository.findById(22));
	}
	
	@Test
	public void testDeleteUser() {
		userRepository.deleteById(2);;
		assertThat(userRepository.existsById(2)).isFalse();
	}
	
	@Test
	public void testUserBorrowedBookList() {
		User user = userRepository.findById(1).get();
		List<Borrow> list = user.getBorrowedBooks();
		assertThat(list).isNotNull();
	}
	
	@Test
	public void testBorrowBook(){
		User user = userRepository.findById(1).get();
		Book book = bookRepository.findById(16).get();
		if(book.isIssued() == false) {
			assertThat(book.isIssued());
		}
	}	
	
	@Test
	public void testReturnedBook(){
		User user = userRepository.findById(1).get();
		Book book = bookRepository.findById(16).get();
		if(book.isIssued()) {
			assertThat(!book.isIssued());
		}
	}
	
}
