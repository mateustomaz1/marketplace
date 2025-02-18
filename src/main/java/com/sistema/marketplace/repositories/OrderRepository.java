package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
