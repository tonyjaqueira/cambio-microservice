package com.tony.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tony.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
