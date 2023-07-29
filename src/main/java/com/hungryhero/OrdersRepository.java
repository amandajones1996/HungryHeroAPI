package com.hungryhero;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends  CrudRepository <Orders, Long> {

    Optional<Orders> findByOrderId(Long orderId); 

    List<Orders> findAll();

    List<Orders> findByRestaurant(String restaurant);

    List<Orders> findByUserId(Long user_id);

    List<Orders> findByDeliveryFrequency(String deliveryFrequency);

    List<Orders> findByTotalAmount(Double totalAmount);
}
