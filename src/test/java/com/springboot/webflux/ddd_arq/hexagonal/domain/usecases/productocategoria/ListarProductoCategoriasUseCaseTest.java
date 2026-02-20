package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.ProductoCategoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
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
@DisplayName("ListarProductoCategoriasUseCase Tests")
class ListarProductoCategoriasUseCaseTest {

    @Mock
    private ProductoCategoriaRepositoryPort repository;

    private ListarProductoCategoriasUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarProductoCategoriasUseCase(repository);
    }

    @Test
    @DisplayName("Debe listar todos los ProductoCategoria")
    void debeListarTodosLosProductoCategorias() {
        // Given
        ProductoCategoria pc1 = new ProductoCategoria(
                "pc-1",
                new ProductoId("prod-1"),
                new CategoriaId("cat-1"));

        ProductoCategoria pc2 = new ProductoCategoria(
                "pc-2",
                new ProductoId("prod-2"),
                new CategoriaId("cat-2"));

        when(repository.findAll()).thenReturn(Flux.just(pc1, pc2));

        // When
        Flux<ProductoCategoria> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .expectNext(pc1)
                .expectNext(pc2)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar flux vacío cuando no hay ProductoCategoria")
    void debeRetornarFluxVacioCuandoNoHayProductoCategorias() {
        // Given
        when(repository.findAll()).thenReturn(Flux.empty());

        // When
        Flux<ProductoCategoria> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }
}
