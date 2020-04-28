package com.spring.fabric.repository;

import com.spring.fabric.model.Product;

public interface ProductRepository {

    Product get(String id);

    Product create(Product product);

    Product update(Product product);

    void delete(String id);

}
