package ms.tiendagen16.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "INVENTARIO")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRODUCTO")
    private Integer producto;

    @Column(name = "CANTIDAD_STOCK")
    private Integer cantidadStock;

}
