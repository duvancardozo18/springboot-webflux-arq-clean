package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import reactor.core.publisher.Flux;

public class ListarCategoriasUseCase {

    private final CategoriaRepositoryPort repository;

    public ListarCategoriasUseCase(CategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<Categoria> execute() {
        return repository.findAll();
    }
}
