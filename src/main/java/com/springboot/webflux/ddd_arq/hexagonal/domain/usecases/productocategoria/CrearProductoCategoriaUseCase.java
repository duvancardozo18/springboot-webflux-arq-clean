package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import reactor.core.publisher.Mono;

public class CrearProductoCategoriaUseCase {

        private final ProductoCategoriaRepositoryPort pcRepository;
        private final ProductoRepositoryPort productoRepository;
        private final CategoriaRepositoryPort categoriaRepository;

        public CrearProductoCategoriaUseCase(ProductoCategoriaRepositoryPort pcRepository,
                        ProductoRepositoryPort productoRepository,
                        CategoriaRepositoryPort categoriaRepository) {
                this.pcRepository = pcRepository;
                this.productoRepository = productoRepository;
                this.categoriaRepository = categoriaRepository;
        }

        public Mono<ProductoCategoria> execute(ProductoId productoId, CategoriaId categoriaId) {
                Mono<Void> validarProducto = productoRepository.findById(productoId)
                                .switchIfEmpty(Mono.error(new NotFoundException("Producto no encontrado")))
                                .then();

                Mono<Void> validarCategoria = categoriaRepository.findById(categoriaId)
                                .switchIfEmpty(Mono.error(new NotFoundException("Categoría no encontrada")))
                                .then();

                ProductoCategoria relacion = new ProductoCategoria(null, productoId, categoriaId);

                return Mono.when(validarProducto, validarCategoria)
                                .then(pcRepository.save(relacion));
        }
}
