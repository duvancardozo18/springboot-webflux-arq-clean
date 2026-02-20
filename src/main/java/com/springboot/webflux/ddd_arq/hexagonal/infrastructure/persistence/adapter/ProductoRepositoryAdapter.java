package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.adapter;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mapper.DomainMapper;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.persistence.mongo.SpringProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final SpringProductoRepository springRepo;
    private final DomainMapper mapper;

    @Override
    public Flux<Producto> findAll() {
        return springRepo.findAll().map(mapper::toDomain);
    }

    @Override
    public Mono<Producto> findById(ProductoId id) {
        return springRepo.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return springRepo.save(mapper.toEntity(producto)).map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(ProductoId id) {
        return springRepo.deleteById(id.value());
    }

    @Override
    public Flux<Producto> findByNombreContaining(Nombre term) {
        return springRepo.findByNombreContainingIgnoreCase(term.value()).map(mapper::toDomain);
    }
}
