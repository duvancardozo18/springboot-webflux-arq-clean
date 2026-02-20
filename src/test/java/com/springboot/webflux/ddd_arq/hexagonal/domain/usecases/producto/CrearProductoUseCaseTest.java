package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
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
@DisplayName("CrearProductoUseCase Tests")
class CrearProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort repository;

    private CrearProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearProductoUseCase(repository);
    }

    @Test
    @DisplayName("Debe crear un producto exitosamente")
    void debeCrearProductoExitosamente() {
        // Given
        Producto productoEntrada = new Producto(
                new ProductoId("prod-1"),
                new Nombre("Laptop"),
                new Precio(999.99));

        Producto productoGuardado = new Producto(
                new ProductoId("prod-123"),
                new Nombre("Laptop"),
                new Precio(999.99));

        when(repository.save(any(Producto.class))).thenReturn(Mono.just(productoGuardado));

        // When
        Mono<Producto> resultado = useCase.execute(productoEntrada);

        // Then
        StepVerifier.create(resultado)
                .expectNext(productoGuardado)
                .verifyComplete();

        verify(repository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe crear producto con id null")
    void debeCrearProductoConIdNull() {
        // Given
        Producto productoEntrada = new Producto(
                null,
                new Nombre("Mouse"),
                new Precio(29.99));

        Producto productoGuardado = new Producto(
                new ProductoId("prod-456"),
                new Nombre("Mouse"),
                new Precio(29.99));

        when(repository.save(any(Producto.class))).thenReturn(Mono.just(productoGuardado));

        // When
        Mono<Producto> resultado = useCase.execute(productoEntrada);

        // Then
        StepVerifier.create(resultado)
                .expectNext(productoGuardado)
                .verifyComplete();
    }
}
