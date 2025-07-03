package ms.tiendagen16.controller;

import jakarta.websocket.server.PathParam;
import ms.tiendagen16.entity.InventarioEntity;
import ms.tiendagen16.response.InventarioResponse;
import ms.tiendagen16.service.impl.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventarioController {

    @Autowired
    InventarioService inventarioService;


     @GetMapping("/inventario")
     public ResponseEntity<List<InventarioResponse>> readAll() {
            List<InventarioResponse> inventarioResponses = inventarioService.readAll();
            return ResponseEntity.ok(inventarioResponses);
     }

    @GetMapping("/inventario/{id}")
    public ResponseEntity<InventarioResponse> readById(@PathVariable(name="id") Integer id) {
        InventarioResponse inventarioResponse = inventarioService.readById(id);
        if (inventarioResponse != null) {
            return ResponseEntity.ok(inventarioResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inventario")
    public ResponseEntity<InventarioResponse> create(@RequestBody InventarioEntity inventarioEntity) {
        InventarioResponse createdInventario = inventarioService.create(inventarioEntity);
        return ResponseEntity.status(201).body(createdInventario);
    }

    @PutMapping("/inventario")
    public ResponseEntity<InventarioResponse> update(@RequestBody InventarioEntity inventarioEntity) {
        InventarioResponse updatedInventario = inventarioService.update(inventarioEntity);
        if (updatedInventario != null) {
            return ResponseEntity.ok(updatedInventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/inventario/{id}")
    public ResponseEntity<String> delete(@PathVariable(name="id") Integer id) {
        String response = inventarioService.deleteById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inventario/queryproducto")
    public ResponseEntity<List<InventarioResponse>> queryByProducto(@PathParam("producto") String producto) {
        List<InventarioResponse> inventarios = inventarioService.queryByProducto(producto);
        if (inventarios != null && !inventarios.isEmpty()) {
            return ResponseEntity.ok(inventarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/inventario/findactivo")
    public ResponseEntity<List<InventarioResponse>> findByActivo(@PathParam("activo") Boolean activo) {
        List<InventarioResponse> inventarios = inventarioService.findByActivo(activo);
        if (inventarios != null && !inventarios.isEmpty()) {
            return ResponseEntity.ok(inventarios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
