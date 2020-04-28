package com.spring.fabric.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.fabric.model.Product;
import com.spring.fabric.model.constant.ModelConstant;
import com.spring.fabric.repository.ProductRepository;
import io.github.ecsoya.fabric.bean.FabricObject;
import io.github.ecsoya.fabric.service.IFabricObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ObjectMapper objectMapper;
    private final IFabricObjectService fabricService;

    @Override
    public Product get(String id) {
        FabricObject fabricObject=fabricService.extGet(id, ModelConstant.PRODUCT_TYPE);
        if(fabricObject!=null){
            Product product = convertFabricObjectToProduct(fabricObject);
            return product;
        }
        return null;

    }

    @Override
    public Product create(Product product) {
        FabricObject fabricObject=convertProductToFabricObject(product);
        fabricService.create(fabricObject);
        return product;
    }

    @Override
    public Product update(Product product) {
        FabricObject fabricObject=convertProductToFabricObject(product);
        fabricService.update(fabricObject);
        return product;
    }

    @Override
    public void delete(String id) {
        fabricService.delete(id,ModelConstant.PRODUCT_TYPE);
    }

    private FabricObject convertProductToFabricObject(Product product){
        FabricObject fabricObject=new FabricObject();
        fabricObject.setId(product.getId());
        fabricObject.setType(product.getType());

        Map<String, Object> values = objectMapper.convertValue(product, new TypeReference<Map<String, Object>>() {});
        fabricObject.setValues(values);
        return fabricObject;
    }

    private Product convertFabricObjectToProduct(FabricObject fabricObject){
        Map<String, Object> values = fabricObject.getValues();

        Product product = objectMapper.convertValue(values, Product.class);
        return product;
    }
}
