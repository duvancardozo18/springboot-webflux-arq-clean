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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CrearCategoriaUseCase Tests")
class CrearCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort repository;

    private CrearCategoriaUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CrearCategoriaUseCase(repository);
    }

    @Test
    @DisplayName("Debe crear una categoría exitosamente")
    void debeCrearCategoriaExitosamente() {
        // Given
        Categoria categoriaEntrada = new Categoria(
                new CategoriaId("cat-1"),
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        Categoria categoriaGuardada = new Categoria(
                new CategoriaId("cat-123"),
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        when(repository.save(any(Categoria.class))).thenReturn(Mono.just(categoriaGuardada));

        // When
        Mono<Categoria> resultado = useCase.execute(categoriaEntrada);

        // Then
        StepVerifier.create(resultado)
                .expectNext(categoriaGuardada)
                .verifyComplete();

        verify(repository, times(1)).save(any(Categoria.class));
    }

    @Test
    @DisplayName("Debe crear categoría con id null")
    void debeCrearCategoriaConIdNull() {
        // Given
        Categoria categoriaEntrada = new Categoria(
                null,
                new Nombre("Ropa"),
                new Descripcion("Prendas de vestir"));

        Categoria categoriaGuardada = new Categoria(
                new CategoriaId("cat-456"),
                new Nombre("Ropa"),
                new Descripcion("Prendas de vestir"));

        when(repository.save(any(Categoria.class))).thenReturn(Mono.just(categoriaGuardada));

        // When
        Mono<Categoria> resultado = useCase.execute(categoriaEntrada);

        // Then
        StepVerifier.create(resultado)
                .expectNext(categoriaGuardada)
                .verifyComplete();
    }
}
