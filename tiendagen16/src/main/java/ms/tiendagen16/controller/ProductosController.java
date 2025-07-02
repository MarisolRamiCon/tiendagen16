package ms.tiendagen16.controller;

import ms.tiendagen16.entity.ProductosEntity;
import ms.tiendagen16.response.ProductosResponse;
import ms.tiendagen16.service.impl.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

     @GetMapping("/productos")
     public ResponseEntity<List<ProductosResponse>> readAll() {
            List<ProductosResponse> productosResponses = productosService.readAll();
            return ResponseEntity.ok(productosResponses);
     }

     @GetMapping("/productos/{id}")
     public ResponseEntity<ProductosResponse> readById(@PathVariable(name="id") Integer id) {
        ProductosResponse productosResponse = productosService.readById(id);
        if (productosResponse != null) {
            return ResponseEntity.ok(productosResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
     }

     @PostMapping("/productos")
     public ResponseEntity<ProductosResponse> create(@RequestBody ProductosEntity productosEntity) {
        ProductosResponse createdProducto = productosService.create(productosEntity);
        return ResponseEntity.status(201).body(createdProducto);
     }

    @PutMapping("/productos")
    public ResponseEntity<ProductosResponse> update(@RequestBody ProductosEntity productosEntity) {
        ProductosResponse updatedProducto = productosService.update(productosEntity);
        if (updatedProducto != null) {
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/productos/querycategoria")
    public ResponseEntity<List<ProductosResponse>> queryByCategoria(@RequestParam("categoria") String categoria) {
        List<ProductosResponse> productosResponses = productosService.queryByCategoria(categoria);
        if (productosResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productosResponses);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String response = productosService.deleteById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

