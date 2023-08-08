package com.hungryhero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;


import com.hungryhero.model.Orders;
import com.hungryhero.model.Users;
import com.hungryhero.repository.UsersRepository;
import java.util.Collections;

import org.springframework.dao.EmptyResultDataAccessException;
// import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsersService implements IUsersService {
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Override
    public List<Users> find() {
        return usersRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users update(Long id, Users updatedUser) {
        Optional<Users> existingUserOptional = usersRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return usersRepository.save(existingUser);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Orders> getOrders(Long id) {
        // Find the user by their userId
        Users user = usersRepository.findById(id).orElse(null);

        // If the user is found, return their orders, otherwise return an empty list
        return user != null ? user.getOrders() : Collections.emptyList();
    }


    public boolean authenticateUser(String email, String password) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);
        
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return user.getPassword().equals(password); // Compare passwords
        }

        return false; // User not found
    }
}
