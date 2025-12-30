package com.order.tracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.tracking.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🔹 Find all orders of a customer (User view)
    List<Order> findByCustomer(String customer);

    // 🔹 Filter orders by merchant
    List<Order> findByMerchant(String merchant);

    // 🔹 Filter orders by status
    List<Order> findByStatus(String status);

    // 🔹 Filter orders by customer AND status
    List<Order> findByCustomerAndStatus(String customer, String status);

    // 🔹 Find active orders (Dashboard)
    List<Order> findByStatusIn(List<String> statuses);

}
