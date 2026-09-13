package com.project.microservices.order_service.repositories;

import com.project.microservices.order_service.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrdersEntity,Long> {
}