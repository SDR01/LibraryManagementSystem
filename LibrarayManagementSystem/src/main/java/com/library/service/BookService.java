package com.library.service;

import java.util.List;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;

public interface BookService {
	
	public Book addBook(Book book);
	
	public List<Book> getAllBookList() throws BookNotFoundException;
	
	public Book deleteBook(Integer bookId) throws BookNotFoundException;
	
	public Book updateBookDetails(Integer bookId, Book book)throws BookNotFoundException;
	
	public List<Book> allBorrowedBooks();
	
	public List<Book> allAvailableBooks();
	
}
