package com.spring.fabric.model;

import com.spring.fabric.model.constant.ModelConstant;
import com.spring.fabric.model.base.BaseEntity;
import lombok.*;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product extends BaseEntity {

    private String type= ModelConstant.PRODUCT_TYPE;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double price;
}
