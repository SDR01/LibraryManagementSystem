package com.library.controller;

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

import com.library.entity.Book;
import com.library.entity.User;
import com.library.exception.BookNotFoundException;
import com.library.exception.UserNotFoundException;
import com.library.service.BookService;
import com.library.service.UserService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		Book newBook = bookService.addBook(book);
		return new ResponseEntity<Book>(newBook, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Book> removeBook(@PathVariable ("id") Integer bookId) throws BookNotFoundException{
		Book deleteBook = bookService.deleteBook(bookId);
		return new ResponseEntity<Book>(deleteBook, HttpStatus.OK);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable ("bookId") Integer bookId, @RequestBody Book book) throws BookNotFoundException{
		Book updatedBook = bookService.updateBookDetails(bookId, book);
		return new ResponseEntity<Book>(updatedBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/allBookList")
	public ResponseEntity<List<Book>> getAllBooks() throws BookNotFoundException{
		List<Book> bookList = bookService.getAllBookList();
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	@GetMapping("/allAvailableBookList")
	public ResponseEntity<List<Book>> getAvailableBooks(){
		List<Book> availableBookList = bookService.allAvailableBooks();
		return new ResponseEntity<List<Book>>(availableBookList, HttpStatus.OK);
	}
	
	@GetMapping("/allBorrowedBookList")
	public ResponseEntity<List<Book>> getBorrowedBooks(){
		List<Book> borrowedBookList = bookService.allBorrowedBooks();
		return new ResponseEntity<List<Book>>(borrowedBookList, HttpStatus.OK);
	}

}
