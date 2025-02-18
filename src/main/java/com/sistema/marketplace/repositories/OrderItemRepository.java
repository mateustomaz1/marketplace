package com.sistema.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.marketplace.entities.OrderItem;
import com.sistema.marketplace.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
