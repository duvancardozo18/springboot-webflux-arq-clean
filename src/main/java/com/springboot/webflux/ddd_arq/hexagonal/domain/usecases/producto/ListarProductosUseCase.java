package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import reactor.core.publisher.Flux;

public class ListarProductosUseCase {

    private final ProductoRepositoryPort repository;

    public ListarProductosUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<Producto> execute() {
        return repository.findAll();
    }
}
