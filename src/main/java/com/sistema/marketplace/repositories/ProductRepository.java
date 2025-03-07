package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {

}
