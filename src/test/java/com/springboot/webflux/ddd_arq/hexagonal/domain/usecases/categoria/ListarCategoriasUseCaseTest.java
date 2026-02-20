package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Descripcion;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
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
@DisplayName("ListarCategoriasUseCase Tests")
class ListarCategoriasUseCaseTest {

    @Mock
    private CategoriaRepositoryPort repository;

    private ListarCategoriasUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarCategoriasUseCase(repository);
    }

    @Test
    @DisplayName("Debe listar todas las categorías")
    void debeListarTodasLasCategorias() {
        // Given
        Categoria categoria1 = new Categoria(
                new CategoriaId("cat-1"),
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        Categoria categoria2 = new Categoria(
                new CategoriaId("cat-2"),
                new Nombre("Ropa"),
                new Descripcion("Prendas de vestir"));

        when(repository.findAll()).thenReturn(Flux.just(categoria1, categoria2));

        // When
        Flux<Categoria> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .expectNext(categoria1)
                .expectNext(categoria2)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar flux vacío cuando no hay categorías")
    void debeRetornarFluxVacioCuandoNoHayCategorias() {
        // Given
        when(repository.findAll()).thenReturn(Flux.empty());

        // When
        Flux<Categoria> resultado = useCase.execute();

        // Then
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }
}
