package com.spring.fabric.controller;

import com.spring.fabric.dto.ProductDTO;
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
    public ResponseEntity<ProductDTO> get(@PathVariable String id) {
        ProductDTO productDTO=productService.get(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.create(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable String id,@RequestBody ProductDTO productDTO) {
       ProductDTO updatedProductDTO = productService.update(id,productDTO);
       return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
