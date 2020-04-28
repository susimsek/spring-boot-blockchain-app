package com.spring.fabric.model.base;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class BaseEntity {

    private String id = UUID.randomUUID().toString();

    private long createdAt = Instant.now().toEpochMilli();

}
