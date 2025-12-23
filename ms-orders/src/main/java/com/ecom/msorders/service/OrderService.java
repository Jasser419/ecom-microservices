package com.ecom.msorders.service;


import com.ecom.msorders.config.OrdersProps;
import com.ecom.msorders.dto.CreateOrderRequest;
import com.ecom.msorders.model.Order;
import com.ecom.msorders.model.OrderItem;
import com.ecom.msorders.model.OrderStatus;
import com.ecom.msorders.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrdersProps props;

    public OrderService(OrderRepository repo, OrdersProps props) {
        this.repo = repo;
        this.props = props;
    }

    public Order create(CreateOrderRequest req) {
        Order order = new Order();
        order.setCustomerId(req.customerId);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(Instant.now());

        if (req.items != null) {
            for (CreateOrderRequest.Item i : req.items) {
                OrderItem item = new OrderItem();
                item.setProductId(i.productId);
                item.setQuantity(i.quantity);
                item.setPrice(i.price);
                order.addItem(item); // important
            }
        }

        return repo.save(order);
    }

    public List<Order> byCustomer(String customerId) {
        return repo.findByCustomerId(customerId);
    }

    public List<Order> recent(String customerId) {
        int days = props.getCommandesLast();
        Instant after = Instant.now().minus(days, ChronoUnit.DAYS);
        return repo.findByCustomerIdAndCreatedAtAfter(customerId, after);
    }
}
