package ms.tiendagen16.controller;

import ms.tiendagen16.entity.DetallesPedidoEntity;
import ms.tiendagen16.response.DetallesPedidoResponse;
import ms.tiendagen16.service.impl.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class DetallesPedidoController {

    @Autowired
    DetallesPedidoService detallesPedidoService;


    @GetMapping("/detalles-pedido")
    public ResponseEntity<List<DetallesPedidoResponse>> readAll() {
        List<DetallesPedidoResponse> detallesPedidoResponses = detallesPedidoService.readAll();
        if (detallesPedidoResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detallesPedidoResponses);
    }

    @GetMapping("/detalles-pedido/{id}")
    public ResponseEntity<DetallesPedidoResponse> readById(@PathVariable(name="id") Integer id) {
        DetallesPedidoResponse detallesPedidoResponse = detallesPedidoService.readById(id);
        if (detallesPedidoResponse != null) {
            return ResponseEntity.ok(detallesPedidoResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/detalles-pedido")
    public ResponseEntity<DetallesPedidoResponse> create(@RequestBody DetallesPedidoEntity detallesPedidoEntity) {
        DetallesPedidoResponse createdDetallesPedido = detallesPedidoService.create(detallesPedidoEntity);
        return ResponseEntity.status(201).body(createdDetallesPedido);
    }

    @PutMapping("/detalles-pedido")
    public ResponseEntity<DetallesPedidoResponse> update(@RequestBody DetallesPedidoEntity detallesPedidoEntity) {
        DetallesPedidoResponse updatedDetallesPedido = detallesPedidoService.update(detallesPedidoEntity);
        if (updatedDetallesPedido != null) {
            return ResponseEntity.ok(updatedDetallesPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/detalles-pedido/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        String response = detallesPedidoService.deleteById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detalles-pedido/querypedidoid")
    public ResponseEntity<List<DetallesPedidoResponse>> queryByPedido(@RequestParam("pedidoid") Integer pedido) {
        List<DetallesPedidoResponse> detallesPedidoResponses = detallesPedidoService.queryByPedidoId(pedido);
        if (detallesPedidoResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detallesPedidoResponses);
    }

}
