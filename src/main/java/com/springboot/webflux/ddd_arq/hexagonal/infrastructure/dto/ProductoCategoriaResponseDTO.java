package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCategoriaResponseDTO {
    private String id;
    private String productoId;
    private String categoriaId;
}
