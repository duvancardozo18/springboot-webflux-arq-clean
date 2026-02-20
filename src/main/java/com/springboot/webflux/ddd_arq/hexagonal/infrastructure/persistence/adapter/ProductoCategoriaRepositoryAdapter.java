package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.adapter;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mapper.DomainMapper;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.ProductoCategoriaMongoEntity;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.ProductoCategoriaMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductoCategoriaRepositoryAdapter implements ProductoCategoriaRepositoryPort {

    private final ProductoCategoriaMongoRepository repository;
    private final DomainMapper mapper;

    @Override
    public Flux<ProductoCategoria> findAll() {
        return repository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<ProductoCategoria> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<ProductoCategoria> save(ProductoCategoria pc) {
        ProductoCategoriaMongoEntity entity = mapper.toEntity(pc);
        return repository.save(entity)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<ProductoCategoria> findByProductoId(ProductoId productoId) {
        return repository.findByProductoId(productoId.value())
                .map(mapper::toDomain);
    }

    @Override
    public Flux<ProductoCategoria> findByCategoriaId(CategoriaId categoriaId) {
        return repository.findByCategoriaId(categoriaId.value())
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
