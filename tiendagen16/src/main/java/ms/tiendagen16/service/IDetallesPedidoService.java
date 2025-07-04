package ms.tiendagen16.service;

import ms.tiendagen16.entity.DetallesPedidoEntity;
import ms.tiendagen16.response.DetallesPedidoResponse;

import java.util.List;

public interface IDetallesPedidoService {

    List<DetallesPedidoResponse> readAll();
    DetallesPedidoResponse readById(Integer id);
    DetallesPedidoResponse create(DetallesPedidoEntity detallesPedidoEntity) throws Exception;
    DetallesPedidoResponse update(DetallesPedidoEntity detallesPedidoEntity) throws Exception;
    String deleteById(Integer id);
    List<DetallesPedidoResponse> queryByPedidoId(Integer pedidoId);
    List<DetallesPedidoResponse> findByProductoId(Integer productoId);

}
