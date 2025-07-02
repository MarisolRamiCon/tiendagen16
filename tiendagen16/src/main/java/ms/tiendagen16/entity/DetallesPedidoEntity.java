package ms.tiendagen16.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DETALLES_PEDIDO")
public class DetallesPedidoEntity {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PEDIDO_ID")
    private Integer pedidoId;

    @Column(name = "PRODUCTO_ID")
    private Integer productoId;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;

    @Column(name = "ACTIVO")
    private Boolean activo;

}
