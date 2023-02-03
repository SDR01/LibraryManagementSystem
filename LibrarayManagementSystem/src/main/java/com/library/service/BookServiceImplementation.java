package com.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;

@Service
public class BookServiceImplementation implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;

	@Override
	public Book addBook(Book book) {
		Book newBookAdded = bookRepository.save(book);
		return newBookAdded;
	}

	@Override
	public List<Book> getAllBookList() throws BookNotFoundException {
		List<Book> bookList = bookRepository.findAll();
		if(bookList.isEmpty()) {
			throw new BookNotFoundException("No Books found");
		}
		else return bookList;
	}

	@Override
	public Book deleteBook(Integer bookId) throws BookNotFoundException {
		Optional<Book> validateBookId = bookRepository.findById(bookId);
		if(validateBookId.isPresent()) {
			Book currentBook = validateBookId.get();
			bookRepository.delete(currentBook);
			return currentBook;
		}
		else {
			throw new BookNotFoundException("Invalid Book ID");
		}
	}

	@Override
	public Book updateBookDetails(Integer bookId, Book book) throws BookNotFoundException {
		Optional<Book> validateBookId = bookRepository.findById(bookId);
		if(validateBookId.isPresent()) {
			Book updatingBook = validateBookId.get();
			updatingBook.setBookId(bookId);
			updatingBook.setTitle(book.getTitle());
			updatingBook.setAuthor(book.getAuthor());
			updatingBook.setCategory(book.getCategory());
			updatingBook.setDescription(book.getDescription());
			updatingBook.setPublisher(book.getPublisher());
			updatingBook.setIssued(book.isIssued());
			Book updatedBook = bookRepository.save(updatingBook);
			return updatedBook;
		}
		else throw new BookNotFoundException("Invalid Book ID");
	}

	@Override
	public List<Book> allBorrowedBooks() {
		List<Book> borrowedBooks = new ArrayList<>();
		List<Book> totalBooks = bookRepository.findAll();
		for(Book books: totalBooks) {
			if(books.isIssued()) {
				borrowedBooks.add(books);
			}
		}
		return borrowedBooks;
	}

	@Override
	public List<Book> allAvailableBooks() {
		List<Book> availableBooks = new ArrayList<>();
		List<Book> totalBooks = bookRepository.findAll();
		for(Book books: totalBooks) {
			if(!books.isIssued()) {
				availableBooks.add(books);
			}
		}
		return availableBooks;
	}

}
