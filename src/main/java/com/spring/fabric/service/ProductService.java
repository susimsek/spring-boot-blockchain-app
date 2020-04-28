package com.spring.fabric.service;


import com.spring.fabric.model.Product;

public interface ProductService {

    Product get(String id);

    Product create(Product product);

    Product update(String id,Product product);

    void delete(String id);

}
