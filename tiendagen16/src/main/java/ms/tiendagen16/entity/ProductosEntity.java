package ms.tiendagen16.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUCTOS")
public class ProductosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "PROVEEDOR")
    private Integer proveedor;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "ACTIVO")
    private Boolean activo;

}
