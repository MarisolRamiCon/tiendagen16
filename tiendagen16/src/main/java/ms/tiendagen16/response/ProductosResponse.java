package ms.tiendagen16.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosResponse {

    private Integer id;
    private String nombre;
    private Double precio;
    private String categoria;
    private String descripcion;
    private Integer stock;

}
