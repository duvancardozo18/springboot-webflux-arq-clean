package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaMongoRepository
        extends ReactiveMongoRepository<CategoriaMongoEntity, String> {
}
