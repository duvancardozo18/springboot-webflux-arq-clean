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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BuscarProductoPorNombreUseCase Tests")
class BuscarProductoPorNombreUseCaseTest {

    @Mock
    private ProductoRepositoryPort repository;

    private BuscarProductoPorNombreUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BuscarProductoPorNombreUseCase(repository);
    }

    @Test
    @DisplayName("Debe buscar productos por nombre")
    void debeBuscarProductosPorNombre() {
        // Given
        Nombre termino = new Nombre("Laptop");
        Producto producto = new Producto(
                new ProductoId("1"),
                new Nombre("Laptop Dell"),
                new Precio(999.99));

        when(repository.findByNombreContaining(any(Nombre.class))).thenReturn(Flux.just(producto));

        // When
        Flux<Producto> resultado = useCase.execute(termino);

        // Then
        StepVerifier.create(resultado)
                .expectNext(producto)
                .verifyComplete();

        verify(repository, times(1)).findByNombreContaining(any(Nombre.class));
    }

    @Test
    @DisplayName("Debe retornar flux vacío cuando no encuentra productos")
    void debeRetornarFluxVacioCuandoNoEncuentraProductos() {
        // Given
        Nombre termino = new Nombre("Inexistente");
        when(repository.findByNombreContaining(any(Nombre.class))).thenReturn(Flux.empty());

        // When
        Flux<Producto> resultado = useCase.execute(termino);

        // Then
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(repository, times(1)).findByNombreContaining(any(Nombre.class));
    }
}
