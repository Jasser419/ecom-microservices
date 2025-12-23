package com.ecom.msorders.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Instant createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // getters/setters
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    public void setCustomerId(String customerId) {
    }

    public void setStatus(OrderStatus orderStatus) {
    }

    public void setCreatedAt(Instant now) {
    }

    // ... getters/setters ...
}
