package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import reactor.core.publisher.Flux;

public class ListarPorProductoUseCase {

    private final ProductoCategoriaRepositoryPort repository;

    public ListarPorProductoUseCase(ProductoCategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<ProductoCategoria> execute(ProductoId productoId) {
        return repository.findByProductoId(productoId);
    }
}
