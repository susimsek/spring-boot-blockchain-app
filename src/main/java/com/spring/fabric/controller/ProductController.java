package com.spring.fabric.controller;

import com.spring.fabric.model.Product;
import com.spring.fabric.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        Product product=productService.get(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody  Product product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id,@RequestBody Product product) {
       Product updatedProduct = productService.update(id,product);
       return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
