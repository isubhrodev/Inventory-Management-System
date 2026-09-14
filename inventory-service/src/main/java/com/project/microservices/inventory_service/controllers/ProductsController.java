package com.project.microservices.inventory_service.controllers;
import com.project.microservices.inventory_service.dtos.ProductDto;
import com.project.microservices.inventory_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")  //you have to add "/api/v1" before adding this endpoint
public class ProductsController {

    private final ProductService productService;

    //from "org.springframework.cloud.client.discovery.DiscoveryClient" package
    private final DiscoveryClient discoveryClient;
    // from "org.springframework.web.client.RestClient" package
    private final RestClient restClient;  // used for third party api call we need to configure it

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        log.info("Fetching all products via controller");
        List<ProductDto> inventories = productService.getAllProducts();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        log.info("Fetching product by id via controller");
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    //test
    @GetMapping("/fetchOrder")
    public String fetchFromOrderService() {

        // from "org.springframework.cloud.client.ServiceInstance" package
        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();  //here this service id came from application name only (go to order application properties you can see the application name)
        // getFirst() for getting first instance
        return restClient.get()
                .uri(orderService.getUri()+"/api/v1/orders/helloOrders")  //got the correct url of orders api
                .retrieve()
                .body(String.class);
    }


}