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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ObtenerCategoriaUseCase Tests")
class ObtenerCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort repository;

    private ObtenerCategoriaUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ObtenerCategoriaUseCase(repository);
    }

    @Test
    @DisplayName("Debe obtener una categoría existente")
    void debeObtenerCategoriaExistente() {
        // Given
        CategoriaId id = new CategoriaId("cat-1");
        Categoria categoria = new Categoria(
                id,
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        when(repository.findById(id)).thenReturn(Mono.just(categoria));

        // When
        Mono<Categoria> resultado = useCase.execute(id);

        // Then
        StepVerifier.create(resultado)
                .expectNext(categoria)
                .verifyComplete();

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException cuando la categoría no existe")
    void debeLanzarNotFoundExceptionCuandoCategoriaNoExiste() {
        // Given
        CategoriaId id = new CategoriaId("cat-inexistente");

        when(repository.findById(id)).thenReturn(Mono.empty());

        // When
        Mono<Categoria> resultado = useCase.execute(id);

        // Then
        StepVerifier.create(resultado)
                .expectError(NotFoundException.class)
                .verify();

        verify(repository, times(1)).findById(id);
    }
}
