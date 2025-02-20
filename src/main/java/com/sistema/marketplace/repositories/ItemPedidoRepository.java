package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.ItemPedido;
import com.sistema.marketplace.entities.pk.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
