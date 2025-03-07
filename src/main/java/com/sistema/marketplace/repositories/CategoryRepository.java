package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, Long> {

}
