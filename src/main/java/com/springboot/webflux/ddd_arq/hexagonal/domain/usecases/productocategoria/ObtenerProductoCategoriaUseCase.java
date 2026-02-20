package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import reactor.core.publisher.Mono;

public class ObtenerProductoCategoriaUseCase {

    private final ProductoCategoriaRepositoryPort repository;

    public ObtenerProductoCategoriaUseCase(ProductoCategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<ProductoCategoria> execute(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Relación producto-categoría no encontrada")));
    }
}
