package com.hungryhero.repository;

import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.hungryhero.model.Users;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;
// import com.hungryhero.jpaonetomanybidirectional.model.Users;

// import com.hungryhero.model.Orders;
import com.hungryhero.model.Users;

import java.util.List;

// @Repository
public interface UsersRepository extends CrudRepository <Users, Long> {
    
    Optional<Users> findById(Long id);
    List<Users> findAllByOrderByIdAsc();
    // List<Orders> getOrders(Long id);
}
