package ms.tiendagen16.repository;

import ms.tiendagen16.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {

    @Query(value = "SELECT * FROM inventario WHERE producto LIKE %:producto%", nativeQuery = true)
    List<InventarioEntity> queryByProducto(String producto);

}
