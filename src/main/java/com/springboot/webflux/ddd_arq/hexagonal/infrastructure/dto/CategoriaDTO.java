package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;
}
