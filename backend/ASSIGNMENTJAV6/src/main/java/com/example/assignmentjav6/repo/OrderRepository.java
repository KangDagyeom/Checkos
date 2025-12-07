package com.example.assignmentjav6.repo;


import com.example.assignmentjav6.entity.Order;
import com.example.assignmentjav6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

