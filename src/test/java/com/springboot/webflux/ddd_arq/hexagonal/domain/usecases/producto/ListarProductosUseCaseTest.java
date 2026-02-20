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
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListarProductosUseCase Tests")
class ListarProductosUseCaseTest {

    @Mock
    private ProductoRepositoryPort repository;

    private ListarProductosUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarProductosUseCase(repository);
    }

    @Test
    @DisplayName("Debe listar todos los productos")
    void debeListarTodosLosProductos() {
        // Given
        Producto producto1 = new Producto(
                new ProductoId("prod-1"),
                new Nombre("Laptop"),
                new Precio(999.99));

        Producto producto2 = new Producto(
                new ProductoId("prod-2"),
                new Nombre("Mouse"),
                new Precio(29.99));

        when(repository.findAll()).thenReturn(Flux.just(producto1, producto2));

        // When
        Flux<Producto> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .expectNext(producto1)
                .expectNext(producto2)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar flux vacío cuando no hay productos")
    void debeRetornarFluxVacioCuandoNoHayProductos() {
        // Given
        when(repository.findAll()).thenReturn(Flux.empty());

        // When
        Flux<Producto> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }
}
