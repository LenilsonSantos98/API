package me.estudos.api_rest_nuvem.service;

import me.estudos.api_rest_nuvem.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);
}
