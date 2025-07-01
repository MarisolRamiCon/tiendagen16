package ms.tiendagen16.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioResponse {

    private Integer id;
    private Integer producto;
    private Integer cantidadStock;

}
