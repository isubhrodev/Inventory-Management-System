package com.project.microservices.order_service.services;

import com.project.microservices.order_service.dtos.OrderRequestDto;
import com.project.microservices.order_service.entities.OrdersEntity;
import com.project.microservices.order_service.repositories.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    public List<OrderRequestDto> getAllOrders(){
        log.info("Fetching all orders");
        List<OrdersEntity> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDto.class))
                .toList();
    }

    public OrderRequestDto getOrderById(Long id){
        log.info("Fetching order by id");
        OrdersEntity order = orderRepo.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

}