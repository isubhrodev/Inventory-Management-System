package com.project.microservices.inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service",path = "/orders")  //name = application name, path = base path
public interface OrderFeignClient {

    @GetMapping("/core/helloOrders")  //use same path and mapping
    String helloOrders();   //use same method as order-service
}