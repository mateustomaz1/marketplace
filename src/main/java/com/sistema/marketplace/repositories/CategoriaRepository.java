package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
