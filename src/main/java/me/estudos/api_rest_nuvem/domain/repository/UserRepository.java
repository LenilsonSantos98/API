package me.estudos.api_rest_nuvem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.estudos.api_rest_nuvem.domain.model.User;

public interface UserRepository extends  JpaRepository<User, Long> {
    
}
