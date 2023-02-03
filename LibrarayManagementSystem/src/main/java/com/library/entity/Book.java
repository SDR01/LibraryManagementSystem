package com.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	
	private String title;
	private String author;
	private String category;
	private String publisher;
	private String description;
	private boolean isIssued;
	
//	@ManyToOne
//	private User user;
	
}
