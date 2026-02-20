package com.springboot.webflux.ddd_arq.hexagonal.domain.repository;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaRepositoryPort {
    Flux<Categoria> findAll();

    Mono<Categoria> findById(CategoriaId id);

    Mono<Categoria> save(Categoria categoria);

    Mono<Void> deleteById(CategoriaId id);
}