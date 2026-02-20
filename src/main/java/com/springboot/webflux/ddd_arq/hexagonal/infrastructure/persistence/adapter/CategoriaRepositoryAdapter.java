package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.adapter;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mapper.DomainMapper;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.CategoriaMongoEntity;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.CategoriaMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaMongoRepository mongoRepository;
    private final DomainMapper mapper;

    @Override
    public Flux<Categoria> findAll() {
        return mongoRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Categoria> findById(CategoriaId id) {
        return mongoRepository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Categoria> save(Categoria categoria) {
        CategoriaMongoEntity entity = mapper.toEntity(categoria);
        return mongoRepository.save(entity)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(CategoriaId id) {
        return mongoRepository.deleteById(id.value());
    }
}