package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producto_categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCategoriaMongoEntity {

    @Id
    private String id;

    private String productoId;     // Relación al producto
    private String categoriaId;    // Relación a la categoría
}