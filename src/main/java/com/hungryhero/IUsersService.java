package com.hungryhero;

import java.util.Optional;
import java.util.List;

public interface IUsersService {

    List<Users> find();
    
    Optional<Users> findById(Long id);

    Users save(Users user);

    Users update(Long id, Users updatedUser);

    void deleteById(Long id);
}
