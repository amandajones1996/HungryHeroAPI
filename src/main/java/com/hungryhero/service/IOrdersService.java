package com.hungryhero.service;

import java.util.Optional;

import com.hungryhero.model.Orders;
// import com.hungryhero.model.Users;
import com.hungryhero.model.Users;

import java.util.List;

public interface IOrdersService {
    
    // Save a new order
    Orders save(Orders order);

    // Update an existing order
    Orders update(Long orderId, Orders updatedOrder);

    // Delete an order by ID
    void deleteById(Long orderId);

    // Get an order by ID
    Optional<Orders> findById(Long orderId);

    // Get all orders
    List<Orders> findAll();

    List<Orders> getOrdersByUser(Users user);
}
