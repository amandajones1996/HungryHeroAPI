package com.hungryhero;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
// import javax.persistence.EntityNotFoundException;

@Service
public class UsersService implements IUsersService {
    
    @Autowired
    private UsersRepository usersRepository;

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
}
