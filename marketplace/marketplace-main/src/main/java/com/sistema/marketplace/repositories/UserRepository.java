package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
