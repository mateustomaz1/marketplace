package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
