package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaMongoEntity {

    @Id
    private String id;

    private String nombre;

    private String descripcion;
}
