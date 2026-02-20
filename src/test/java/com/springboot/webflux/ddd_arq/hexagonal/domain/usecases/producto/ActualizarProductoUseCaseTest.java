package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Precio;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ActualizarProductoUseCase Tests")
class ActualizarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort repository;

    private ActualizarProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ActualizarProductoUseCase(repository);
    }

    @Test
    @DisplayName("Debe actualizar un producto existente")
    void debeActualizarProductoExistente() {
        // Given
        ProductoId id = new ProductoId("prod-1");

        Producto productoExistente = new Producto(
                id,
                new Nombre("Laptop Vieja"),
                new Precio(899.99));

        Producto productoActualizado = new Producto(
                id,
                new Nombre("Laptop Nueva"),
                new Precio(999.99));

        when(repository.findById(id)).thenReturn(Mono.just(productoExistente));
        when(repository.save(any(Producto.class))).thenReturn(Mono.just(productoActualizado));

        // When
        Mono<Producto> resultado = useCase.execute(id, productoActualizado);

        // Then
        StepVerifier.create(resultado)
                .expectNext(productoActualizado)
                .verifyComplete();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException cuando el producto no existe")
    void debeLanzarNotFoundExceptionCuandoProductoNoExiste() {
        // Given
        ProductoId id = new ProductoId("prod-inexistente");
        Producto producto = new Producto(
                id,
                new Nombre("Laptop"),
                new Precio(999.99));

        when(repository.findById(id)).thenReturn(Mono.empty());

        // When
        Mono<Producto> resultado = useCase.execute(id, producto);

        // Then
        StepVerifier.create(resultado)
                .expectError(NotFoundException.class)
                .verify();

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Producto.class));
    }
}
