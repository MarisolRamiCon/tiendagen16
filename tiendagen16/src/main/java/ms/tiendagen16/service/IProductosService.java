package ms.tiendagen16.service;

import ms.tiendagen16.entity.ProductosEntity;
import ms.tiendagen16.response.ProductosResponse;

import java.util.List;
import java.util.Optional;

public interface IProductosService {

    // Metodos Read
    List<ProductosResponse> readAll();

    ProductosResponse readById(Integer id);

    // Metodo create
    ProductosResponse create(ProductosEntity productoEntity);

    // Metodo update
    ProductosResponse update(ProductosEntity productoEntity);

    // Metodo Delete
    String deleteById(Integer id);

    // Metodo para buscar productos por categoria
    List<ProductosResponse> queryByCategoria(String categoria);

    // Metodo para buscar productos por proveedor
    List<ProductosResponse> findByProveedor(Integer proveedor);

}
