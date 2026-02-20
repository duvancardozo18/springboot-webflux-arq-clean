package com.springboot.webflux.ddd_arq.hexagonal.infrastructure.config;

import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.CategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoCategoriaRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.repository.ProductoRepositoryPort;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.categoria.*;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.producto.*;
import com.springboot.webflux.ddd_arq.hexagonal.domain.usecases.productocategoria.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    // Producto Use Cases
    @Bean
    public CrearProductoUseCase crearProductoUseCase(ProductoRepositoryPort repository) {
        return new CrearProductoUseCase(repository);
    }

    @Bean
    public ListarProductosUseCase listarProductosUseCase(ProductoRepositoryPort repository) {
        return new ListarProductosUseCase(repository);
    }

    @Bean
    public ObtenerProductoUseCase obtenerProductoUseCase(ProductoRepositoryPort repository) {
        return new ObtenerProductoUseCase(repository);
    }

    @Bean
    public ActualizarProductoUseCase actualizarProductoUseCase(ProductoRepositoryPort repository) {
        return new ActualizarProductoUseCase(repository);
    }

    @Bean
    public EliminarProductoUseCase eliminarProductoUseCase(ProductoRepositoryPort repository) {
        return new EliminarProductoUseCase(repository);
    }

    @Bean
    public BuscarProductoPorNombreUseCase buscarProductoPorNombreUseCase(ProductoRepositoryPort repository) {
        return new BuscarProductoPorNombreUseCase(repository);
    }

    // Categoria Use Cases
    @Bean
    public CrearCategoriaUseCase crearCategoriaUseCase(CategoriaRepositoryPort repository) {
        return new CrearCategoriaUseCase(repository);
    }

    @Bean
    public ListarCategoriasUseCase listarCategoriasUseCase(CategoriaRepositoryPort repository) {
        return new ListarCategoriasUseCase(repository);
    }

    @Bean
    public ObtenerCategoriaUseCase obtenerCategoriaUseCase(CategoriaRepositoryPort repository) {
        return new ObtenerCategoriaUseCase(repository);
    }

    @Bean
    public ActualizarCategoriaUseCase actualizarCategoriaUseCase(CategoriaRepositoryPort repository) {
        return new ActualizarCategoriaUseCase(repository);
    }

    @Bean
    public EliminarCategoriaUseCase eliminarCategoriaUseCase(CategoriaRepositoryPort repository) {
        return new EliminarCategoriaUseCase(repository);
    }

    // ProductoCategoria Use Cases
    @Bean
    public CrearProductoCategoriaUseCase crearProductoCategoriaUseCase(
            ProductoCategoriaRepositoryPort pcRepository,
            ProductoRepositoryPort productoRepository,
            CategoriaRepositoryPort categoriaRepository) {
        return new CrearProductoCategoriaUseCase(pcRepository, productoRepository, categoriaRepository);
    }

    @Bean
    public ListarProductoCategoriasUseCase listarProductoCategoriasUseCase(
            ProductoCategoriaRepositoryPort repository) {
        return new ListarProductoCategoriasUseCase(repository);
    }

    @Bean
    public ObtenerProductoCategoriaUseCase obtenerProductoCategoriaUseCase(
            ProductoCategoriaRepositoryPort repository) {
        return new ObtenerProductoCategoriaUseCase(repository);
    }

    @Bean
    public EliminarProductoCategoriaUseCase eliminarProductoCategoriaUseCase(
            ProductoCategoriaRepositoryPort repository) {
        return new EliminarProductoCategoriaUseCase(repository);
    }

    @Bean
    public ListarPorProductoUseCase listarPorProductoUseCase(ProductoCategoriaRepositoryPort repository) {
        return new ListarPorProductoUseCase(repository);
    }

    @Bean
    public ListarPorCategoriaUseCase listarPorCategoriaUseCase(ProductoCategoriaRepositoryPort repository) {
        return new ListarPorCategoriaUseCase(repository);
    }
}
