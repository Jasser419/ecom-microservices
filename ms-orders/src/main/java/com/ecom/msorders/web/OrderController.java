package com.ecom.msorders.web;


import com.ecom.msorders.dto.CreateOrderRequest;
import com.ecom.msorders.model.Order;
import com.ecom.msorders.repo.OrderRepository;
import com.ecom.msorders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderRepository repo;

    public OrderController(OrderService service, OrderRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Order> byCustomer(@RequestParam String customerId) {
        return service.byCustomer(customerId);
    }

    @GetMapping("/recent")
    public List<Order> recent(@RequestParam String customerId) {
        return service.recent(customerId);
    }
}
