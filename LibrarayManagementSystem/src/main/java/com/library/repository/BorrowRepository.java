package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer>{

}
