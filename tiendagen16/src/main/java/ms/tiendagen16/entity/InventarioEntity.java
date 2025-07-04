package ms.tiendagen16.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INVENTARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCTO")
    private Integer producto;

    @Column(name = "CANTIDAD_STOCK")
    private Integer cantidadStock;

    @Column(name = "ACTIVO")
    private Boolean activo;

}
