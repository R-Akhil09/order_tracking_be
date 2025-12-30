package com.order.tracking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.tracking.model.Order;
import com.order.tracking.model.OrderHistory;
import com.order.tracking.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImp {

    private final OrderRepository repository;

    public OrderServiceImp(OrderRepository repository) {
        this.repository = repository;
    }

    // 🔹 GET ALL ORDERS
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // 🔹 GET ORDER BY ID
    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // 🔹 CREATE ORDER
    public Order createOrder(Order order) {

        LocalDateTime now = LocalDateTime.now();

        order.setStatus("CREATED");

        // ✅ Initial history
        OrderHistory history = new OrderHistory();
        history.setStatus("CREATED");
        history.setTime(now);
        history.setSource("SYSTEM");
        history.setOrder(order);

        order.getHistory().add(history);

        return repository.save(order);
    }

    // 🔹 UPDATE ORDER STATUS
    public Order updateStatus(Long id, String status) {

        Order order = getOrderById(id);
        LocalDateTime now = LocalDateTime.now();

        order.setStatus(status);

        OrderHistory history = new OrderHistory();
        history.setStatus(status);
        history.setTime(now);
        history.setSource("ADMIN");
        history.setOrder(order);

        order.getHistory().add(history);

        return repository.save(order);
    }

    // 🔹 DELETE ORDER
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
