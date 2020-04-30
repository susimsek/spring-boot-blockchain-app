package com.spring.fabric.service.impl;

import com.spring.fabric.dto.ProductDTO;
import com.spring.fabric.model.Product;
import com.spring.fabric.repository.ProductRepository;
import com.spring.fabric.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO get(String id) {
        Product product = productRepository.get(id);
        if(product!=null){
            return modelMapper.map(product,ProductDTO.class);
        }
        return null;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        product=productRepository.create(product);
        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product=modelMapper.map(productDTO,Product.class);
        Product updatedProduct = productRepository.get(id);
        if(updatedProduct!=null){
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            productRepository.update(updatedProduct);
        }
        return modelMapper.map(updatedProduct,ProductDTO.class);
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.get(id);
        if(product!=null){
            productRepository.delete(id);
        }
    }
}
