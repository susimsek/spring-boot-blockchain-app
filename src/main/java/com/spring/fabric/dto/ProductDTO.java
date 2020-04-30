package com.spring.fabric.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private Double price;

    private long createdAt = Instant.now().toEpochMilli();
}
