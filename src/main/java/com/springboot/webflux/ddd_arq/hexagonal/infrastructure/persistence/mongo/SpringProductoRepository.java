package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SpringProductoRepository extends ReactiveMongoRepository<ProductoMongoEntity, String> {
    Flux<ProductoMongoEntity> findByNombreContainingIgnoreCase(String term);
}