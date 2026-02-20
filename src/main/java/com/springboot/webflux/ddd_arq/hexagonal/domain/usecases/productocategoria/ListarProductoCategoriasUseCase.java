package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import reactor.core.publisher.Flux;

public class ListarProductoCategoriasUseCase {

    private final ProductoCategoriaRepositoryPort repository;

    public ListarProductoCategoriasUseCase(ProductoCategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<ProductoCategoria> execute() {
        return repository.findAll();
    }
}
