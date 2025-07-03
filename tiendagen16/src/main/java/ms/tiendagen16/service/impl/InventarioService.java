package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.InventarioEntity;
import ms.tiendagen16.repository.InventarioRepository;
import ms.tiendagen16.response.InventarioResponse;
import ms.tiendagen16.service.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    private Function<InventarioEntity,InventarioResponse> mapToDtoResponse = new Function<InventarioEntity, InventarioResponse>() {
        @Override
        public InventarioResponse apply(InventarioEntity inventarioEntity) {
            InventarioResponse inventarioResponse = new InventarioResponse();
            inventarioResponse.setId(inventarioEntity.getId());
            inventarioResponse.setProducto(inventarioEntity.getProducto());
            inventarioResponse.setCantidadStock(inventarioEntity.getCantidadStock());
            return inventarioResponse;
        }
    };

     public InventarioResponse readById(Integer id) {
         Optional<InventarioEntity> inventario = inventarioRepository.findById(id);
         if(inventario.isPresent()) {
            return mapToDtoResponse.apply(inventario.get());
         }else{
            return null; // or throw an exception if preferred
         }
     }

    public List<InventarioResponse> readAll() {
          return inventarioRepository.findAll()
                                     .stream()
                                     .filter(p-> p.getActivo())
                                     .map(mapToDtoResponse)
                                     .toList();
     }

     public InventarioResponse create(InventarioEntity inventarioEntity) {
            InventarioEntity inventarioEntitySaved = inventarioRepository.save(inventarioEntity);
            return mapToDtoResponse.apply(inventarioEntitySaved);
     }

     public InventarioResponse update(InventarioEntity inventarioEntity) {
            Optional<InventarioEntity> existingInventario = inventarioRepository.findById(inventarioEntity.getId());
            if (existingInventario.isPresent()) {
                InventarioEntity updatedInventarioEntity = inventarioRepository.save(inventarioEntity);
                return mapToDtoResponse.apply(updatedInventarioEntity);
            } else {
                return null; // or throw an exception if preferred
            }
     }

     public String deleteById(Integer id) {
         Optional<InventarioEntity> inventarioOpt = inventarioRepository.findById(id);
        if (inventarioOpt.isPresent()) {
            String response = "El inventario con ID " + id + " ha sido eliminado.";
            InventarioEntity inventarioEntity = inventarioOpt.get();
            inventarioEntity.setActivo(false);
            inventarioRepository.save(inventarioEntity);
           // inventarioRepository.deleteById(id);
            return response;
        }else{
            return null; // or throw an exception if preferred
        }

     }

        public List<InventarioResponse> queryByProducto(String producto) {

            return inventarioRepository.queryByProducto(producto)
                                        .stream()
                                        .filter(p -> p.getActivo())
                                        .map(mapToDtoResponse)
                                        .toList();
        }

    @Override
    public List<InventarioResponse> findByActivo(Boolean activo) {
        return inventarioRepository.findByActivo(activo)
                                    .stream()
                                    .map(mapToDtoResponse)
                                    .toList();
    }

}
