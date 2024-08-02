package me.estudos.api_rest_nuvem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.estudos.api_rest_nuvem.domain.model.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long> {
    
}
