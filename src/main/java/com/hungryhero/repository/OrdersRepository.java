package com.hungryhero.repository;

import org.springframework.data.repository.CrudRepository;

import com.hungryhero.model.Orders;
// import com.hungryhero.model.Users;


import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends  CrudRepository <Orders, Long> {

    Optional<Orders> findByOrderId(Long orderId); 

    List<Orders> findAll();

    List<Orders> findByRestaurant(String restaurant);

    List<Orders> findByUserId(Long userId);
    
}
