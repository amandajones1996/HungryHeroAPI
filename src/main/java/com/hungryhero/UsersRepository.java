package com.hungryhero;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UsersRepository extends CrudRepository <Users, Long> {
    
    Optional<Users> findById(Long id);
    List<Users> findAllByOrderByIdAsc();
}
