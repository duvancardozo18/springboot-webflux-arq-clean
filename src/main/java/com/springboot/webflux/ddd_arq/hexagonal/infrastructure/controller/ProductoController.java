package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.controller;

import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.dto.ProductoResponseDTO;
import com.springboot.webflux.ddd_arq.hexagonal.infrastructure.mapper.DtoMapper;
import com.springboot.webflux.ddd_arq.hexagonal.domain.entities.Producto;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto.*;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.ProductoId;
import com.springboot.webflux.ddd_arq.hexagonal.domain.valueobjects.Nombre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final CrearProductoUseCase crearProductoUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final ObtenerProductoUseCase obtenerProductoUseCase;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final EliminarProductoUseCase eliminarProductoUseCase;
    private final BuscarProductoPorNombreUseCase buscarProductoPorNombreUseCase;
    private final DtoMapper mapper;

    @GetMapping
    public Flux<ProductoResponseDTO> listar() {
        return listarProductosUseCase.execute()
                .map(mapper::toResponseDTO);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductoResponseDTO>> obtener(@PathVariable String id) {
        return obtenerProductoUseCase.execute(mapper.toProductoId(id))
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductoResponseDTO>> crear(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = mapper.toDomain(dto);
        return crearProductoUseCase.execute(producto)
                .map(saved -> {
                    ProductoResponseDTO response = mapper.toResponseDTO(saved);
                    return ResponseEntity.created(URI.create("/api/productos/" + saved.id().value()))
                            .body(response);
                });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductoResponseDTO>> actualizar(@PathVariable String id,
            @Valid @RequestBody ProductoDTO dto) {
        Producto producto = mapper.toDomain(id, dto);
        return actualizarProductoUseCase.execute(mapper.toProductoId(id), producto)
                .map(mapper::toResponseDTO)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
        return eliminarProductoUseCase.execute(mapper.toProductoId(id))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/buscar")
    public Flux<ProductoResponseDTO> buscar(@RequestParam String q) {
        return buscarProductoPorNombreUseCase.execute(mapper.toNombre(q))
                .map(mapper::toResponseDTO);
    }
}