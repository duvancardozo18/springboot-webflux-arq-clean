package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import reactor.core.publisher.Mono;

public class CrearCategoriaUseCase {

    private final CategoriaRepositoryPort repository;

    public CrearCategoriaUseCase(CategoriaRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Categoria> execute(Categoria categoria) {
        Categoria nuevaCategoria = new Categoria(null, categoria.nombre(), categoria.descripcion());
        return repository.save(nuevaCategoria);
    }
}
