package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("productos")
public class ProductoMongoEntity {
    @Id
    private String id;
    private String nombre;
    private Double precio;
}