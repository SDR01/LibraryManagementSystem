package com.library.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<MyErrorDetails> bookExceptionHandler(BookNotFoundException e, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserNotFoundException e, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BorrowException.class)
	public ResponseEntity<MyErrorDetails> borrowExceptionHandler(BorrowException e, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<MyErrorDetails> invalidExceptionHandler(InvalidException e, WebRequest req){
		MyErrorDetails me = new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception e, WebRequest req){
		MyErrorDetails me = new MyErrorDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me, HttpStatus.BAD_REQUEST);
	}

}
