package ms.tiendagen16.service;

import ms.tiendagen16.entity.InventarioEntity;
import ms.tiendagen16.response.InventarioResponse;

import java.util.List;

public interface IInventarioService {



    List<InventarioResponse> readAll();
    InventarioResponse readById(Integer id);
    InventarioResponse create(InventarioEntity inventarioEntity) throws Exception;
    InventarioResponse update(InventarioEntity inventarioEntity) throws Exception;
    String deleteById(Integer id);
    List<InventarioResponse> queryByProducto(String producto);
    List<InventarioResponse> findByActivo(Boolean activo);

}
