package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.ProductosEntity;
import ms.tiendagen16.repository.ProductosRepository;
import ms.tiendagen16.response.ProductosResponse;
import ms.tiendagen16.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductosService implements IProductosService {


    @Autowired
    private ProductosRepository productosRepository;

    Function<ProductosEntity, ProductosResponse> mapToResponse = new Function<ProductosEntity, ProductosResponse>() {
        @Override
        public ProductosResponse apply(ProductosEntity productosEntity) {
            ProductosResponse productosResponse = new ProductosResponse();
            productosResponse.setId(productosEntity.getId());
            productosResponse.setNombre(productosEntity.getNombre());
            productosResponse.setDescripcion(productosEntity.getDescripcion());
            productosResponse.setPrecio(productosEntity.getPrecio());
            productosResponse.setCategoria(productosEntity.getCategoria());
            productosResponse.setStock(productosEntity.getStock());
            return productosResponse;
        }
    };


    @Override
    public List<ProductosResponse> readAll() {
        List<ProductosEntity> productosEntities = productosRepository.findAll();
        if (productosEntities.isEmpty()) {
            return List.of();
        }
        // Filtrar los productos activos y mapearlos a ProductosResponse
        return productosEntities.stream()
                .filter(producto -> producto.getActivo())
                .map(mapToResponse)
                .toList();
    }

    @Override
    public ProductosResponse readById(Integer id) {
        Optional<ProductosEntity> productoEntityOptional = productosRepository.findById(id);
        if (productoEntityOptional.isPresent()) {
            ProductosEntity productoEntity = productoEntityOptional.get();
            return mapToResponse.apply(productoEntity);
        }else{
            return null; //Or throw new ProductoNotFoundException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public ProductosResponse create(ProductosEntity productoEntity) {
        ProductosEntity savedEntity = productosRepository.save(productoEntity);
        return mapToResponse.apply(savedEntity);
    }

    @Override
    public ProductosResponse update(ProductosEntity productoEntity) {
        Optional<ProductosEntity> existingEntity = productosRepository.findById(productoEntity.getId());
        if (existingEntity.isPresent()) {
            ProductosEntity updatedEntity = productosRepository.save(productoEntity);
            return mapToResponse.apply(updatedEntity);
        }else{
           return null; // Or throw new ProductoNotFoundException("Producto no encontrado con ID: " + productoEntity.getId());
        }
    }

    @Override
    public String deleteById(Integer id) {
        Optional<ProductosEntity> productoEntityOptional = productosRepository.findById(id);
        if (productoEntityOptional.isPresent()) {
            ProductosEntity productoEntity = productoEntityOptional.get();
            // Marcar el producto como inactivo en lugar de eliminarlo
            productoEntity.setActivo(false);
            productosRepository.save(productoEntity);
            return "Producto con ID " + id + " ha sido eliminado."; //(marcado como inactivo)
        }else{
            return null;
        }
    }

    @Override
    public List<ProductosResponse> queryByCategoria(String categoria) {
        List<ProductosEntity> productosEntities = productosRepository.queryByCategoria(categoria);
        if (productosEntities.isEmpty()) {
            return List.of();
        }
        // Filtrar los productos activos y mapearlos a ProductosResponse
        return productosEntities.stream()
                .filter(producto -> producto.getActivo())
                .map(mapToResponse)
                .toList();
    }

    @Override
    public List<ProductosResponse> findByProveedor(Integer proveedor) {
        List<ProductosEntity> productosEntities = productosRepository.findByProveedor(proveedor);
        if (productosEntities.isEmpty()) {
            return List.of();
        }
        // Filtrar los productos activos y mapearlos a ProductosResponse
        return productosEntities.stream()
                .filter(producto -> producto.getActivo())
                .map(mapToResponse)
                .toList();
    }

}
