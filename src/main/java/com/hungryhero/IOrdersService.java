package com.hungryhero;

import java.util.Optional;
import java.util.List;

public interface IOrdersService {
    
    // Save a new order
    Orders save(Orders order);

    // Update an existing order
    Orders update(Orders order);

    // Delete an order by ID
    void deleteById(Long orderId);

    // Get an order by ID
    Optional<Orders> findById(Long orderId);

    // Get all orders
    List<Orders> findAll();

    // Get orders by user ID
    List<Orders> findByUserId(Long user_id);

    
}
