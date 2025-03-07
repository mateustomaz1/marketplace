package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {

}
