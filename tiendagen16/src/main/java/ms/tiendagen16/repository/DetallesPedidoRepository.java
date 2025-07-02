package ms.tiendagen16.repository;

import ms.tiendagen16.entity.DetallesPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetallesPedidoRepository  extends JpaRepository<DetallesPedidoEntity, Integer> {


    @Query(value = "SELECT * FROM detalles_pedido WHERE pedido_id = :pedidoId", nativeQuery = true)
    List<DetallesPedidoEntity> queryByPedidoId(Integer pedidoId);

}