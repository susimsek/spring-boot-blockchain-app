package com.spring.fabric.service.impl;

import com.spring.fabric.model.Product;
import com.spring.fabric.repository.ProductRepository;
import com.spring.fabric.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product get(String id) {
        Product product = productRepository.get(id);
        if(product!=null){
            return product;
        }
        return null;
    }

    @Override
    public Product create(Product product) {
        return productRepository.create(product);

    }

    @Override
    public Product update(String id, Product product) {
        Product updatedProduct = productRepository.get(id);
        if(updatedProduct!=null){
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            productRepository.update(updatedProduct);
        }
        return updatedProduct;
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.get(id);
        if(product!=null){
            productRepository.delete(id);
        }
    }
}
