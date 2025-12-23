package com.ecom.msorders.dto;


import java.util.List;

public class CreateOrderRequest {
    public String customerId;
    public List<Item> items;

    public static class Item {
        public Long productId;
        public int quantity;
        public double price; // pour l’instant on le reçoit; plus tard Feign calcule
    }
}
