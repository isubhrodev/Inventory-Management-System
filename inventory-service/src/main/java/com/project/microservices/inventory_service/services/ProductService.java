package com.project.microservices.inventory_service.services;

import com.project.microservices.inventory_service.dtos.ProductDto;
import com.project.microservices.inventory_service.entities.ProductEntity;
import com.project.microservices.inventory_service.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;


    public List<ProductDto> getAllProducts() {
        log.info("Fetching all inventory items");
        List<ProductEntity> inventories = productRepo.findAll();
        return inventories.stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductDto.class))
                //.collect(Collectors.toList());
                .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with Id: {}",id);
        Optional<ProductEntity> inventory = productRepo.findById(id);
        return inventory.map(item -> modelMapper.map(item,ProductDto.class))
                .orElseThrow(()->new RuntimeException("Inventory not found"));
    }

}
