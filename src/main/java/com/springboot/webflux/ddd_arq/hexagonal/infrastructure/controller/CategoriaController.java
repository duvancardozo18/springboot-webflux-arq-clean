package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.controller;

import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.CategoriaResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.mapper.DtoMapper;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Categoria;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria.*;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CrearCategoriaUseCase crearCategoriaUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final ObtenerCategoriaUseCase obtenerCategoriaUseCase;
    private final ActualizarCategoriaUseCase actualizarCategoriaUseCase;
    private final EliminarCategoriaUseCase eliminarCategoriaUseCase;
    private final DtoMapper mapper;

    @PostMapping
    public Mono<CategoriaResponseDTO> crear(@RequestBody CategoriaDTO dto) {
        Categoria categoria = mapper.toDomain(dto);
        return crearCategoriaUseCase.execute(categoria)
                .map(mapper::toResponseDTO);
    }

    @GetMapping
    public Flux<CategoriaResponseDTO> listar() {
        return listarCategoriasUseCase.execute()
                .map(mapper::toResponseDTO);
    }

    @GetMapping("/{id}")
    public Mono<CategoriaResponseDTO> obtener(@PathVariable String id) {
        return obtenerCategoriaUseCase.execute(mapper.toCategoriaId(id))
                .map(mapper::toResponseDTO);
    }

    @PutMapping("/{id}")
    public Mono<CategoriaResponseDTO> actualizar(@PathVariable String id, @RequestBody CategoriaDTO dto) {
        Categoria categoria = mapper.toDomain(id, dto);
        return actualizarCategoriaUseCase.execute(mapper.toCategoriaId(id), categoria)
                .map(mapper::toResponseDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable String id) {
        return eliminarCategoriaUseCase.execute(mapper.toCategoriaId(id));
    }
}