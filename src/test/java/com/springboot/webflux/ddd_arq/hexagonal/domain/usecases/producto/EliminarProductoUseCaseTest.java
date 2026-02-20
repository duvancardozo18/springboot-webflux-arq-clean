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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EliminarProductoUseCase Tests")
class EliminarProductoUseCaseTest {

    @Mock
    private ProductoRepositoryPort repository;

    private EliminarProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new EliminarProductoUseCase(repository);
    }

    @Test
    @DisplayName("Debe eliminar un producto existente")
    void debeEliminarProductoExistente() {
        // Given
        ProductoId id = new ProductoId("prod-1");
        Producto producto = new Producto(
                id,
                new Nombre("Laptop"),
                new Precio(999.99));

        when(repository.findById(id)).thenReturn(Mono.just(producto));
        when(repository.deleteById(id)).thenReturn(Mono.empty());

        // When
        Mono<Void> resultado = useCase.execute(id);

        // Then
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException cuando el producto no existe")
    void debeLanzarNotFoundExceptionCuandoProductoNoExiste() {
        // Given
        ProductoId id = new ProductoId("prod-inexistente");

        when(repository.findById(id)).thenReturn(Mono.empty());

        // When
        Mono<Void> resultado = useCase.execute(id);

        // Then
        StepVerifier.create(resultado)
                .expectError(NotFoundException.class)
                .verify();

        verify(repository, times(1)).findById(id);
        verify(repository, never()).deleteById(any());
    }
}
