package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.InventarioEntity;
import ms.tiendagen16.repository.InventarioRepository;
import ms.tiendagen16.response.InventarioResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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


@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @InjectMocks
    private InventarioService inventarioService;

    @Mock
    private InventarioRepository inventarioRepository;

    // Mocked data can be added here if needed
     private List<InventarioEntity> mockedListEntity;
     private List<InventarioResponse> mockedListResponse;


    @BeforeEach
    void setUp() {

        // Initialize any necessary data or mocks here
         mockedListEntity = List.of(
             new InventarioEntity(1, 1, 100, true),
             new InventarioEntity(2, 2, 50, true),
             new InventarioEntity(3, 3, 200, true)
         );
         mockedListResponse = mockedListEntity.stream()
             .map(inventarioService.mapToDtoResponse)
             .toList();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readById() {

        // Mock the behavior of the repository
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));

        // Call the service method
        InventarioResponse response = inventarioService.readById(mockEntity.getId());

        // Verify the response
        assertNotNull(response);
        assertEquals(mockEntity.getId(), response.getId());
        assertEquals(mockEntity.getProducto(), response.getProducto());
        assertEquals(mockEntity.getCantidadStock(), response.getCantidadStock());

    }

    @Test
    void readByIdDoesNotExists() {

        // Mock the behavior of the repository to return an empty Optional
        Integer idNotFound = 999;
        Mockito.when(inventarioRepository.findById(idNotFound)).thenReturn(Optional.empty());

        // Call the service method
        InventarioResponse response = inventarioService.readById(idNotFound);

        // Verify the response is null
        assertNull(response);

    }

    @Test
    void readAll() {

        // Mock the behavior of the repository
        Mockito.when(inventarioRepository.findAll()).thenReturn(mockedListEntity);

        // Call the service method
        List<InventarioResponse> response = inventarioService.readAll();

        // Verify the response
        assertNotNull(response);
        assertEquals(mockedListEntity.size(), response.size());
        for (int i = 0; i < mockedListEntity.size(); i++) {
            assertEquals(mockedListResponse.get(i).getId(), response.get(i).getId());
            assertEquals(mockedListResponse.get(i).getProducto(), response.get(i).getProducto());
            assertEquals(mockedListResponse.get(i).getCantidadStock(), response.get(i).getCantidadStock());
        }

    }

    @Test
    void create() throws Exception {

        // Mock the behavior of the repository
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.save(mockEntity)).thenReturn(mockEntity);

        // Call the service method
        InventarioResponse response = inventarioService.create(mockEntity);

        // Verify the response
        assertNotNull(response);
        assertEquals(mockEntity.getId(), response.getId());
        assertEquals(mockEntity.getProducto(), response.getProducto());
        assertEquals(mockEntity.getCantidadStock(), response.getCantidadStock());
    }

    @Test
    void createThrowsException() {

        // Mock the behavior of the repository to throw an exception
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.save(mockEntity)).thenThrow(new RuntimeException("Error saving entity"));

        // Call the service method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            inventarioService.create(mockEntity);
        });

        // Verify the exception message
        assertEquals("Error al crear el inventario: Error saving entity", exception.getMessage());

    }


    @Test
    void update() throws Exception {

        // Mock the behavior of the repository
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));
        Mockito.when(inventarioRepository.save(mockEntity)).thenReturn(mockEntity);

        // Call the service method
        InventarioResponse response = inventarioService.update(mockEntity);

        // Verify the response
        assertNotNull(response);
        assertEquals(mockEntity.getId(), response.getId());
        assertEquals(mockEntity.getProducto(), response.getProducto());
        assertEquals(mockEntity.getCantidadStock(), response.getCantidadStock());

    }

    @Test
    void updateDoesNotExists() throws Exception {

        // Mock the behavior of the repository to return an empty Optional
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.findById(mockEntity.getId())).thenReturn(Optional.empty());

        // Call the service method
        InventarioResponse inventarioResponse = inventarioService.update(mockEntity);

        // Verify that the method returns null or throws an exception
        Assertions.assertNull(inventarioResponse);

    }

    @Test
    void updateThrowsException() {

        // Mock the behavior of the repository to throw an exception
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.findById(mockEntity.getId())).thenReturn(Optional.of(mockEntity));
        Mockito.when(inventarioRepository.save(mockEntity)).thenThrow(new RuntimeException("Error updating entity"));

        // Call the service method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            inventarioService.update(mockEntity);
        });

        // Verify the exception message
        assertEquals("Error al actualizar el inventario: Error updating entity", exception.getMessage());

    }


    @Test
    void deleteById() {

        // Mock the behavior of the repository
        Integer idToDelete = 1;
        InventarioEntity mockEntity = mockedListEntity.get(0);
        Mockito.when(inventarioRepository.findById(idToDelete)).thenReturn(Optional.of(mockEntity));

        // Call the service method
        String response = inventarioService.deleteById(idToDelete);

        // Verify the response
        assertNotNull(response);
        assertEquals("El inventario con ID " + idToDelete + " ha sido eliminado.", response);


    }

    @Test
    void deleteByIdDoesNotExists() {

        // Mock the behavior of the repository to return an empty Optional
        Integer idNotFound = 999;
        Mockito.when(inventarioRepository.findById(idNotFound)).thenReturn(Optional.empty());

        // Call the service method
        String response = inventarioService.deleteById(idNotFound);

        // Verify the response is null or appropriate message
        assertNull(response);

    }

    @Test
    void queryByProducto() {

        // Mock the behavior of the repository
        String producto = "Producto1";
        Mockito.when(inventarioRepository.queryByProducto(producto)).thenReturn(mockedListEntity);

        // Call the service method
        List<InventarioResponse> response = inventarioService.queryByProducto(producto);

        // Verify the response
        assertNotNull(response);
        assertEquals(mockedListResponse.size(), response.size());
        for (int i = 0; i < mockedListResponse.size(); i++) {
            assertEquals(mockedListResponse.get(i).getId(), response.get(i).getId());
            assertEquals(mockedListResponse.get(i).getProducto(), response.get(i).getProducto());
            assertEquals(mockedListResponse.get(i).getCantidadStock(), response.get(i).getCantidadStock());
        }

    }

    @Test
    void findByActivo() {

        // Mock the behavior of the repository
        Boolean activo = true;
        Mockito.when(inventarioRepository.findByActivo(activo)).thenReturn(mockedListEntity);

        // Call the service method
        List<InventarioResponse> response = inventarioService.findByActivo(activo);

        // Verify the response
        assertNotNull(response);
        assertEquals(mockedListResponse.size(), response.size());
        for (int i = 0; i < mockedListResponse.size(); i++) {
            assertEquals(mockedListResponse.get(i).getId(), response.get(i).getId());
            assertEquals(mockedListResponse.get(i).getProducto(), response.get(i).getProducto());
            assertEquals(mockedListResponse.get(i).getCantidadStock(), response.get(i).getCantidadStock());
        }

    }


}