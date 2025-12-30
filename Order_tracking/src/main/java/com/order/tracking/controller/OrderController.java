package com.order.tracking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.order.tracking.model.Order;
import com.order.tracking.service.OrderServiceImp;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderServiceImp service;

    // ✅ Constructor injection (recommended)
    public OrderController(OrderServiceImp service) {
        this.service = service;
    }

    // 🔹 GET ALL ORDERS
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    // 🔹 GET ORDER BY ID (for OrderDetails page)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    // 🔹 CREATE NEW ORDER
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created = service.createOrder(order);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 🔹 UPDATE ORDER STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Order updated = service.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // 🔹 DELETE ORDER (optional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
