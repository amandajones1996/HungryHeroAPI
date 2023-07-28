package com.hungryhero;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
    
    Optional<Users> findById(Long id);

}
