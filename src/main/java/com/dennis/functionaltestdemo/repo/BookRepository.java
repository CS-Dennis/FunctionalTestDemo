package com.dennis.functionaltestdemo.repo;


import com.dennis.functionaltestdemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
