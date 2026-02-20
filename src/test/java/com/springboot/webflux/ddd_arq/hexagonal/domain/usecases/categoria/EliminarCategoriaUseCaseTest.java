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
@DisplayName("EliminarCategoriaUseCase Tests")
class EliminarCategoriaUseCaseTest {

    @Mock
    private CategoriaRepositoryPort repository;

    private EliminarCategoriaUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new EliminarCategoriaUseCase(repository);
    }

    @Test
    @DisplayName("Debe eliminar una categoría existente")
    void debeEliminarCategoriaExistente() {
        // Given
        CategoriaId id = new CategoriaId("cat-1");
        Categoria categoria = new Categoria(
                id,
                new Nombre("Electrónica"),
                new Descripcion("Productos electrónicos"));

        when(repository.findById(id)).thenReturn(Mono.just(categoria));
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
    @DisplayName("Debe lanzar NotFoundException cuando la categoría no existe")
    void debeLanzarNotFoundExceptionCuandoCategoriaNoExiste() {
        // Given
        CategoriaId id = new CategoriaId("cat-inexistente");

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
