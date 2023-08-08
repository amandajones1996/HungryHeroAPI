package com.hungryhero.service;

import java.util.Optional;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.hungryhero.model.Orders;
import com.hungryhero.model.Users;

import java.util.List;

public interface IUsersService {

    List<Users> find();
    
    Optional<Users> findById(Long id);

    Users save(Users user);

    Users update(Long id, Users updatedUser);

    void deleteById(Long id);

    List<Orders> getOrders(Long id);

}
