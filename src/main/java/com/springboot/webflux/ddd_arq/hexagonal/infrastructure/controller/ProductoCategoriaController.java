package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.controller;

import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoCategoriaDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoCategoriaResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.mapper.DtoMapper;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria.*;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.CategoriaId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/producto-categorias")
@RequiredArgsConstructor
public class ProductoCategoriaController {

    private final CrearProductoCategoriaUseCase crearProductoCategoriaUseCase;
    private final ListarProductoCategoriasUseCase listarProductoCategoriasUseCase;
    private final DtoMapper mapper;

    @PostMapping
    public Mono<ProductoCategoriaResponseDTO> crear(@RequestBody ProductoCategoriaDTO dto) {
        return crearProductoCategoriaUseCase.execute(
                mapper.toProductoId(dto.getProductoId()),
                mapper.toCategoriaId(dto.getCategoriaId()))
                .map(mapper::toResponseDTO);
    }

    @GetMapping
    public Flux<ProductoCategoriaResponseDTO> listar() {
        return listarProductoCategoriasUseCase.execute()
                .map(mapper::toResponseDTO);
    }
}
