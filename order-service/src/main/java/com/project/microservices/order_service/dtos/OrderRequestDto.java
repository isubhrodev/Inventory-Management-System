package com.project.microservices.order_service.dtos;

import com.project.microservices.order_service.entities.OrderItemsEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
//    private Long id;
//    private List<OrderItemsEntity> items;
//    private Double totalPrice;

    private List<OrderRequestItemDto> items;
}

