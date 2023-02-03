package com.library.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int borrowId;
	
	private Integer bookId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private Integer fineAmount;

}
