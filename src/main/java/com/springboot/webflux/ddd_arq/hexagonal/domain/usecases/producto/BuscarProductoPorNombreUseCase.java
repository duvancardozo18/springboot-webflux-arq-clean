package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import reactor.core.publisher.Flux;

public class BuscarProductoPorNombreUseCase {

    private final ProductoRepositoryPort repository;

    public BuscarProductoPorNombreUseCase(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public Flux<Producto> execute(Nombre term) {
        return repository.findByNombreContaining(term);
    }
}
