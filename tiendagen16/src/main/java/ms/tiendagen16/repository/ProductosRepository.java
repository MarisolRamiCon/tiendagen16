package ms.tiendagen16.repository;

import ms.tiendagen16.entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepository extends JpaRepository<ProductosEntity, Integer> {

    // Método para encontrar productos por categoría
    @Query(value = "SELECT * FROM productos WHERE categoria = :categoria", nativeQuery = true)
    List<ProductosEntity> queryByCategoria(String categoria);

}
