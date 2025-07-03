package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.DetallesPedidoEntity;
import ms.tiendagen16.repository.DetallesPedidoRepository;
import ms.tiendagen16.response.DetallesPedidoResponse;
import ms.tiendagen16.service.IDetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DetallesPedidoService implements IDetallesPedidoService {

    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    Function<DetallesPedidoEntity,DetallesPedidoResponse> mapToResponse = new Function<DetallesPedidoEntity, DetallesPedidoResponse>() {
        @Override
        public DetallesPedidoResponse apply(DetallesPedidoEntity detallesPedidoEntity) {
            DetallesPedidoResponse detallesPedidoResponse = new DetallesPedidoResponse();
            detallesPedidoResponse.setId(detallesPedidoEntity.getId());
            detallesPedidoResponse.setProductoId(detallesPedidoEntity.getProductoId());
            detallesPedidoResponse.setPedidoId(detallesPedidoEntity.getPedidoId());
            detallesPedidoResponse.setCantidad(detallesPedidoEntity.getCantidad());
            detallesPedidoResponse.setPrecioUnitario(detallesPedidoEntity.getPrecioUnitario());

            return detallesPedidoResponse;
        }
    };

    @Override
    public List<DetallesPedidoResponse> readAll() {

        return detallesPedidoRepository.findAll()
                .stream()
                .filter(p-> p.getActivo())
                .map(mapToResponse)
                .toList();

    }

    @Override
    public DetallesPedidoResponse readById(Integer id) {
        return detallesPedidoRepository.findById(id)
                .filter(p -> p.getActivo())
                .map(mapToResponse)
                .orElse(null);
    }

    @Override
    public DetallesPedidoResponse create(DetallesPedidoEntity detallesPedidoEntity) throws Exception {
        DetallesPedidoEntity savedEntity = null;
        try{
            savedEntity = detallesPedidoRepository.save(detallesPedidoEntity);
        }catch(Exception e){
           throw new Exception("Error al guardar el detalle del pedido: " + e.getMessage());
        }

        return mapToResponse.apply(savedEntity);
    }

    @Override
    public DetallesPedidoResponse update(DetallesPedidoEntity detallesPedidoEntity) throws Exception {
        Optional<DetallesPedidoEntity> findedEntityOpt = detallesPedidoRepository.findById(detallesPedidoEntity.getId());
        if(findedEntityOpt.isPresent()) {
            DetallesPedidoEntity updatedEntity = null;
            try {
                updatedEntity = detallesPedidoRepository.save(detallesPedidoEntity);
                return mapToResponse.apply(updatedEntity);
            }catch(Exception e){
                throw new Exception("Error al actualizar el detalle del pedido: " + e.getMessage());
            }

        }else{
            return null; // or throw an exception if you prefer
        }

    }

    @Override
    public String deleteById(Integer id) {
        Optional<DetallesPedidoEntity> detallesPedidoEntityOpt = detallesPedidoRepository.findById(id);
        if(detallesPedidoEntityOpt.isPresent()){
            DetallesPedidoEntity detallesPedidoEntity = detallesPedidoEntityOpt.get();
            detallesPedidoEntity.setActivo(false);
            detallesPedidoRepository.save(detallesPedidoEntity);
            return "Detalle de pedido eliminado correctamente";
        } else {
            return null; // or throw an exception if you prefer
        }
    }

    @Override
    public List<DetallesPedidoResponse> queryByPedidoId(Integer pedidoId) {
        return detallesPedidoRepository.queryByPedidoId(pedidoId)
                .stream()
                .filter(p -> p.getActivo())
                .map(mapToResponse)
                .toList();
    }

    @Override
    public List<DetallesPedidoResponse> findByProductoId(Integer productoId) {
        return detallesPedidoRepository.findByProductoId(productoId)
                .stream()
                .filter(p -> p.getActivo())
                .map(mapToResponse)
                .toList();
    }

}
