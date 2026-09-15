package com.project.microservices.order_service.controllers;

import com.project.microservices.order_service.dtos.OrderRequestDto;
import com.project.microservices.order_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@Slf4j
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        log.info("Fetching order by id via controller");
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }


    //Dummy API
    @GetMapping("/helloOrders")
    public String helloOrders(){
        return "Hello from OrderService";
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        //The OrderRequestDto class is already created inside the previous article.

        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);

    }

}