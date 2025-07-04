package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.DetallesPedidoEntity;
import ms.tiendagen16.repository.DetallesPedidoRepository;
import ms.tiendagen16.response.DetallesPedidoResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetallesPedidoServiceTest {

    @InjectMocks
    private DetallesPedidoService detallesPedidoService;

    @Mock
    private DetallesPedidoRepository detallesPedidoRepository;


    private List<DetallesPedidoEntity> mockedListEntity;
    private List<DetallesPedidoResponse> mockedListResponse;


    @BeforeEach
    void setUp() {

        // Initialize any necessary data or mocks here

        mockedListEntity = List.of(
            new DetallesPedidoEntity(1, 101, 201, 2, 50.0, true),
            new DetallesPedidoEntity(2, 102, 202, 1, 100.0, true),
            new DetallesPedidoEntity(3, 103, 203, 5, 20.0, true),
            new DetallesPedidoEntity(201,201,201, 2, 50.0,true)
        );

        mockedListResponse = mockedListEntity.stream()
            .map(detallesPedidoService.mapToResponse)
            .toList();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readAll() {
         Mockito.when(detallesPedidoRepository.findAll()).thenReturn(mockedListEntity);
         List<DetallesPedidoResponse> response = detallesPedidoService.readAll();
         assertNotNull(response);
         assertEquals(mockedListEntity.size(), response.size());
    }

    @Test
    void readById() {
        Mockito.when(detallesPedidoRepository.findById(1)).thenReturn(Optional.ofNullable(mockedListEntity.get(0)));
        DetallesPedidoResponse response = detallesPedidoService.readById(1);
        assertNotNull(response);
        assertEquals(mockedListResponse.get(0).getId(), response.getId());
    }

    @Test
    void create() throws Exception {
        DetallesPedidoEntity newEntity = new DetallesPedidoEntity(4, 104, 204, 3, 30.0, true);
        Mockito.when(detallesPedidoRepository.save(newEntity)).thenReturn(newEntity);
        DetallesPedidoResponse response = detallesPedidoService.create(newEntity);
        assertNotNull(response);
        assertEquals(newEntity.getId(), response.getId());
    }

    @Test
    void createThrowsException() throws Exception {
        DetallesPedidoEntity newEntity = new DetallesPedidoEntity(4, 104, 204, 3, 30.0, true);
        Mockito.when(detallesPedidoRepository.save(newEntity)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> { detallesPedidoService.create(newEntity);});

        assertEquals("Error al guardar el detalle del pedido: Database error", exception.getMessage());
    }

    @Test
    void update() throws Exception{
        DetallesPedidoEntity updatedEntity = new DetallesPedidoEntity(1, 101, 201, 3, 55.0, true);
        Mockito.when(detallesPedidoRepository.findById(1)).thenReturn(Optional.ofNullable(mockedListEntity.get(0)));
        Mockito.when(detallesPedidoRepository.save(updatedEntity)).thenReturn(updatedEntity);

        DetallesPedidoResponse response = detallesPedidoService.update(updatedEntity);
        assertNotNull(response);
        assertEquals(updatedEntity.getId(), response.getId());
        assertEquals(updatedEntity.getCantidad(), response.getCantidad());
    }

    @Test
    void updateDoesNotExists() throws Exception {
        DetallesPedidoEntity updatedEntity = new DetallesPedidoEntity(99, 999, 999, 3, 55.0, true);
        Mockito.when(detallesPedidoRepository.findById(99)).thenReturn(Optional.empty());

        DetallesPedidoResponse response = detallesPedidoService.update(updatedEntity);
        assertNull(response);
    }

    @Test
    void updateThrowsException() throws Exception {
        DetallesPedidoEntity updatedEntity = new DetallesPedidoEntity(1, 101, 201, 3, 55.0, true);
        Mockito.when(detallesPedidoRepository.findById(1)).thenReturn(Optional.ofNullable(mockedListEntity.get(0)));
        Mockito.when(detallesPedidoRepository.save(updatedEntity)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> { detallesPedidoService.update(updatedEntity); });

        assertEquals("Error al actualizar el detalle del pedido: Database error", exception.getMessage());
    }

    @Test
    void deleteById() {
        Mockito.when(detallesPedidoRepository.findById(1)).thenReturn(Optional.ofNullable(mockedListEntity.get(0)));
        String response = detallesPedidoService.deleteById(1);
        assertNotNull(response);
        assertTrue(response.contains("Detalle de pedido eliminado correctamente"));
    }

    @Test
    void deleteByIdDoesNotExists() {
        Mockito.when(detallesPedidoRepository.findById(99)).thenReturn(Optional.empty());
        String response = detallesPedidoService.deleteById(99);
        assertNull(response);
    }


    @Test
    void queryByPedidoId() {
        Integer pedidoId = 201;
        List<DetallesPedidoEntity> filteredList = mockedListEntity.stream()
            .filter(entity -> entity.getPedidoId().equals(pedidoId))
            .toList();

        Mockito.when(detallesPedidoRepository.queryByPedidoId(pedidoId)).thenReturn(filteredList);

        List<DetallesPedidoResponse> response = detallesPedidoService.queryByPedidoId(pedidoId);
        assertNotNull(response);
        assertEquals(filteredList.size(), response.size());
    }

    @Test
    void queryByPedidoIdDoesNotExists() {
        Integer pedidoId = 999;
        Mockito.when(detallesPedidoRepository.queryByPedidoId(pedidoId)).thenReturn(List.of());

        List<DetallesPedidoResponse> response = detallesPedidoService.queryByPedidoId(pedidoId);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    @Test
    void findByProductoId() {

        Integer productoId = 201;
        List<DetallesPedidoEntity> filteredList = mockedListEntity.stream()
            .filter(entity -> entity.getProductoId().equals(productoId))
            .toList();

        Mockito.when(detallesPedidoRepository.findByProductoId(productoId)).thenReturn(filteredList);

        List<DetallesPedidoResponse> response = detallesPedidoService.findByProductoId(productoId);
        assertNotNull(response);
        assertEquals(filteredList.size(), response.size());


    }

    @Test
    void findByProductoIdDoesNotExists() {
        Integer productoId = 999;
        Mockito.when(detallesPedidoRepository.findByProductoId(productoId)).thenReturn(List.of());

        List<DetallesPedidoResponse> response = detallesPedidoService.findByProductoId(productoId);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }


}