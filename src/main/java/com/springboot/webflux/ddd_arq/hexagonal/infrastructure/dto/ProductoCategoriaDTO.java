package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductoCategoriaDTO {
    @NotBlank
    private String productoId;

    @NotBlank
    private String categoriaId;
}