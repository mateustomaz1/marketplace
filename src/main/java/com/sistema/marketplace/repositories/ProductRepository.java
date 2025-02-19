package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
