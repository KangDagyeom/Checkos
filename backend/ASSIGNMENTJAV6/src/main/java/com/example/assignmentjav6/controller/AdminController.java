package com.example.assignmentjav6.controller;

import com.example.assignmentjav6.entity.Book;
import com.example.assignmentjav6.repo.BookRepository;
import com.example.assignmentjav6.repo.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BookRepository bookRepo;
    private final OrderRepository orderRepo;

    public AdminController(BookRepository bookRepo, OrderRepository orderRepo) { this.bookRepo = bookRepo; this.orderRepo = orderRepo; }

    @GetMapping("/orders")
    public List<?> allOrders() { return orderRepo.findAll(); }

    @GetMapping("/books")
    public List<Book> allBooks() { return bookRepo.findAll(); }

    @PostMapping("/books")
    public Book create(@RequestBody Book b) { return bookRepo.save(b); }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book b) {
        var exist = bookRepo.findById(id).orElse(null);
        if (exist == null) return null;
        exist.setTitle(b.getTitle()); exist.setAuthor(b.getAuthor()); exist.setPrice(b.getPrice()); exist.setDescription(b.getDescription());
        return bookRepo.save(exist);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) { bookRepo.deleteById(id); }
}

