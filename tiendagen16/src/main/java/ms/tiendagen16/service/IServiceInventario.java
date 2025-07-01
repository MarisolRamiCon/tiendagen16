package ms.tiendagen16.service;

import ms.tiendagen16.entity.InventarioEntity;
import ms.tiendagen16.response.InventarioResponse;

import java.util.List;

public interface IServiceInventario {



    List<InventarioResponse> readAll();
    InventarioResponse readById(Integer id);
    InventarioResponse create(InventarioEntity inventarioEntity);
    InventarioResponse update(InventarioEntity inventarioEntity);
    String deleteById(Integer id);
    List<InventarioResponse> queryByProducto(String producto);

}
