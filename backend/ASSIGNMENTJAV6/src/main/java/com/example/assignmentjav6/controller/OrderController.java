package com.example.assignmentjav6.controller;


import com.example.assignmentjav6.entity.Order;
import com.example.assignmentjav6.entity.OrderItem;
import com.example.assignmentjav6.repo.BookRepository;
import com.example.assignmentjav6.repo.OrderRepository;
import com.example.assignmentjav6.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepo, BookRepository bookRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    static class ItemReq {
        public Long bookId;
        public Integer quantity;
    }

    static class OrderReq {
        public List<ItemReq> items;
    }

    @PostMapping
    public ResponseEntity<?> place(@RequestBody OrderReq req, Authentication auth) {
        if (auth == null) return ResponseEntity.status(401).build();
        var userOpt = userRepo.findByUsername(auth.getName());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).build();
        var user = userOpt.get();
        Order o = new Order();
        o.setUser(user);
        List<OrderItem> items = new ArrayList<>();
        for (var it : req.items) {
            var b = bookRepo.findById(it.bookId).orElse(null);
            if (b == null) continue;
            OrderItem oi = new OrderItem();
            oi.setBook(b);
            oi.setQuantity(it.quantity == null ? 1 : it.quantity);
            oi.setPrice(b.getPrice());
            items.add(oi);
        }
        o.setItems(items);
        orderRepo.save(o);
        return ResponseEntity.ok(Map.of("orderId", o.getId()));
    }

    @GetMapping
    public ResponseEntity<?> myOrders(Authentication auth) {
        if (auth == null) return ResponseEntity.status(401).build();
        var userOpt = userRepo.findByUsername(auth.getName());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).build();
        var orders = orderRepo.findByUser(userOpt.get());
        return ResponseEntity.ok(orders);
    }
}

