package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
