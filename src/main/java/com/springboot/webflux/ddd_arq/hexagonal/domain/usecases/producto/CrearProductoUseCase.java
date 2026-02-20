package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import reactor.core.publisher.Mono;

public class CrearProductoUseCase {

    private final ProductoRepositoryPort repository;

    public CrearProductoUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Producto> execute(Producto producto) {
        Producto nuevoProducto = new Producto(null, producto.nombre(), producto.precio());
        return repository.save(nuevoProducto);
    }
}
