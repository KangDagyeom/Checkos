package com.example.assignmentjav6.repo;




import com.example.assignmentjav6.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}

