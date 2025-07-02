package ms.tiendagen16.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallesPedidoResponse {

    private Integer id;
    private Integer pedidoId;
    private Integer productoId;
    private Integer cantidad;
    private Double precioUnitario;

}
