package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
