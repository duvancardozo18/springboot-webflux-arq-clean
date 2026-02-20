package com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria;

import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.exceptions.NotFoundException;
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
@DisplayName("ActualizarCategoriaUseCase Tests")
class ActualizarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort repository;

    private ActualizarCategoriaUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ActualizarCategoriaUseCase(repository);
    }

    @Test
    @DisplayName("Debe actualizar una categoría existente")
    void debeActualizarCategoriaExistente() {
        // Given
        CategoriaId id = new CategoriaId("cat-1");

        Categoria categoriaExistente = new Categoria(
                id,
                new Nombre("Electrónica Vieja"),
                new Descripcion("Descripción vieja"));

        Categoria categoriaActualizada = new Categoria(
                id,
                new Nombre("Electrónica Nueva"),
                new Descripcion("Descripción nueva"));

        when(repository.findById(id)).thenReturn(Mono.just(categoriaExistente));
        when(repository.save(any(Categoria.class))).thenReturn(Mono.just(categoriaActualizada));

        // When
        Mono<Categoria> resultado = useCase.execute(id, categoriaActualizada);

        // Then
        StepVerifier.create(resultado)
                .expectNext(categoriaActualizada)
                .verifyComplete();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Categoria.class));
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException cuando la categoría no existe")
    void debeLanzarNotFoundExceptionCuandoCategoriaNoExiste() {
        // Given
        CategoriaId id = new CategoriaId("cat-inexistente");
        Categoria categoria = new Categoria(
                id,
                new Nombre("Electrónica"),
                new Descripcion("Descripción"));

        when(repository.findById(id)).thenReturn(Mono.empty());

        // When
        Mono<Categoria> resultado = useCase.execute(id, categoria);

        // Then
        StepVerifier.create(resultado)
                .expectError(NotFoundException.class)
                .verify();

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any(Categoria.class));
    }
}
