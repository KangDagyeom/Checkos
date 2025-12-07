package com.example.assignmentjav6.repo;


import com.example.assignmentjav6.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

