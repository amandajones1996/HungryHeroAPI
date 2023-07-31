package com.hungryhero.service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.dao.EmptyResultDataAccessException;

import com.hungryhero.model.Orders;
import com.hungryhero.model.Users;
// import com.hungryhero.model.Users;
import com.hungryhero.repository.OrdersRepository;

@Service
public class OrdersService implements IOrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;


    public OrdersService(OrdersRepository ordersRepository) {
        super();
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long orderId) {
        return ordersRepository.findByOrderId(orderId);
    }

    @Override
    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public Orders update(Long orderId, Orders updatedOrder) {
        Optional<Orders> orderOptional = ordersRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            // Order with the given orderId not found, return null or throw an exception
            throw new EmptyResultDataAccessException(1);
        }

        Orders existingOrder = orderOptional.get();
        
        // Update the fields of the existing order with the new values from updatedOrder
        existingOrder.setRestaurant(updatedOrder.getRestaurant());
        existingOrder.setFoodOrder(updatedOrder.getFoodOrder());
        existingOrder.setDeliveryFrequency(updatedOrder.getDeliveryFrequency());
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());

        // Save the updated order back to the database
        return ordersRepository.save(existingOrder);
    }

    @Override
    public void deleteById(Long orderId) {
        ordersRepository.deleteById(orderId);
    }

    // @Override
    // public List<Orders> findByUserId(Long userId) {
    //     return ordersRepository.findByUserId(userId);
    // }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }
}