package com.hungryhero;

import java.util.Optional;

public interface IUsersService {
    
    Optional<Users> findById(Long id);

    Users save(Users user);

    Users update(Long id, Users updatedUser);

    void deleteById(Long id);
}
