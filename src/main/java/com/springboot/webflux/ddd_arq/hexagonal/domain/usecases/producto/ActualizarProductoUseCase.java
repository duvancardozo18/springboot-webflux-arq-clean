package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import reactor.core.publisher.Mono;

public class ActualizarProductoUseCase {

    private final ProductoRepositoryPort repository;

    public ActualizarProductoUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Producto> execute(ProductoId id, Producto producto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Producto no encontrado")))
                .flatMap(existing -> {
                    Producto actualizado = new Producto(id, producto.nombre(), producto.precio());
                    return repository.save(actualizado);
                });
    }
}
