package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
