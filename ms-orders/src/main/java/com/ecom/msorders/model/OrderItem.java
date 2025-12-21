package com.ecom.msorders.model;


import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantity;
    private double price; // prix au moment de l'achat

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public void setOrder(Order order) {
    }

    public void setProductId(Long productId) {
    }

    public void setQuantity(int quantity) {
    }

    public void setPrice(double price) {
    }

    // getters/setters
}
