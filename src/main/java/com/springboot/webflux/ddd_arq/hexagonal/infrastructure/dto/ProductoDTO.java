package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoDTO {
    @NotBlank
    private String nombre;

    @NotNull(message = "El precio no puede ser null")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;
}