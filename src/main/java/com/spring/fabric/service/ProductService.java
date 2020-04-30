package com.spring.fabric.service;

import com.spring.fabric.dto.ProductDTO;

public interface ProductService {

    ProductDTO get(String id);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(String id,ProductDTO productDTO);

    void delete(String id);

}
