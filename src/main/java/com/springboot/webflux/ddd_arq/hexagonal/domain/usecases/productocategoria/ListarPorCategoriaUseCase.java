package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Flux;

public class ListarPorCategoriaUseCase {

    private final ProductoCategoriaRepositoryPort repository;

    public ListarPorCategoriaUseCase(ProductoCategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<ProductoCategoria> execute(CategoriaId categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }
}
