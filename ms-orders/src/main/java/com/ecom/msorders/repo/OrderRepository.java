package com.ecom.msorders.repo;


import com.ecom.msorders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByCustomerIdAndCreatedAtAfter(String customerId, Instant after);
}
