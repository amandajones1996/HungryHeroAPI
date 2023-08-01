package com.hungryhero.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.hungryhero.model.Orders;
import com.hungryhero.model.Users;
import com.hungryhero.service.OrdersService;
import com.hungryhero.service.UsersService;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
// import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UsersService usersService;

    
    public OrdersController(OrdersService ordersService, UsersService usersService) {
        super();
        this.ordersService = ordersService;
        this.usersService = usersService;
    }

    // Get all orders
    @GetMapping("/orders")
    public List<Orders> findAll() {
        return ordersService.findAll();
    }

    // Get an order by ID
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long orderId) {
        Optional<Orders> orderOptional = ordersService.findById(orderId);
        
        // Check if the user exists
        Orders order = orderOptional.orElse(null);
            
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        // return the order as a response
        return ResponseEntity.ok(order);
    }

    // Create a new order
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Orders> createOrder(@RequestBody Orders newOrder) {
        Orders savedOrder = ordersService.save(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // Update an existing order
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long  orderId, @RequestBody Orders updatedOrderRequest) {
        Orders existingOrder = ordersService.update(orderId, updatedOrderRequest);

        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingOrder);
    }

    // Delete an order by ID
    @DeleteMapping("/orders/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
            ordersService.deleteById(orderId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
    // @GetMapping("/users/{userId}")
    // public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable Long id) {
    //     // Implement the logic to fetch orders by user ID
    //     // You can use the UsersService to find the user by their ID
    //     Optional<Users> userOptional= usersService.findById(id);
    //     Users user = userOptional.orElse(null);
    //     if (user != null) {
    //         List<Orders> orders = ordersService.getOrdersByUser(user);
    //         return ResponseEntity.ok(orders);
    //     } else {
    //         // Return 404 Not Found response
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @GetMapping("orders/users/{userId}")
    public ResponseEntity<List<Orders>> getOrdersByUserId(@PathVariable Long userId) {
         // Check if the user exists
        Optional<Users> userOptional = usersService.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // User exists, get the orders
        List<Orders> orders = ordersService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
