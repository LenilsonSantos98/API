package me.estudos.api_rest_nuvem.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.estudos.api_rest_nuvem.domain.model.User;
import me.estudos.api_rest_nuvem.domain.repository.UserRepository;
import me.estudos.api_rest_nuvem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("this Account number already exists");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new );
    }
    
}
