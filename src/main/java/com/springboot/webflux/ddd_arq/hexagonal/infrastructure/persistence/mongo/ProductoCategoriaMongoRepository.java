package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductoCategoriaMongoRepository
        extends ReactiveMongoRepository<ProductoCategoriaMongoEntity, String> {

    Flux<ProductoCategoriaMongoEntity> findByProductoId(String productoId);
    Flux<ProductoCategoriaMongoEntity> findByCategoriaId(String categoriaId);
}
