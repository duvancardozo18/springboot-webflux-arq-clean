package com.springboot.webflux.ddd_arq.hexagonal.domain.entities;

import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Precio;

public record Producto(
                ProductoId id,
                Nombre nombre,
                Precio precio) {
}
