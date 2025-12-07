package com.example.assignmentjav6.controller;


import com.example.assignmentjav6.entity.Book;
import com.example.assignmentjav6.repo.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository repo;
    public BookController(BookRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Book> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) { return repo.findById(id).orElse(null); }
}

