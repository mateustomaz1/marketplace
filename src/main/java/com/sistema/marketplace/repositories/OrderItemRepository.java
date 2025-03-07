package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.OrderItem;
import com.sistema.marketplace.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
