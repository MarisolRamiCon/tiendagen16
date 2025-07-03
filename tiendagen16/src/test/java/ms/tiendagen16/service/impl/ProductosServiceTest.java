package ms.tiendagen16.service.impl;

import ms.tiendagen16.entity.ProductosEntity;
import ms.tiendagen16.repository.ProductosRepository;
import ms.tiendagen16.response.ProductosResponse;
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

@ExtendWith(MockitoExtension.class)
class ProductosServiceTest {

    @InjectMocks
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    // Mocked data can be added here if needed
    private List<ProductosEntity> mockedListEntity;
    private List<ProductosResponse> mockedListResponse;


    @BeforeEach
    void setUp() {

        // Initialize any necessary data or mocks here
        mockedListEntity = List.of(new ProductosEntity(1, "Producto 1", "Descripcion 1", 100.0, "Categoria 1",1, 10, true),
                                   new ProductosEntity(2, "Producto 2", "Descripcion 2", 200.0, "Categoria 2",2, 20, true),
                                   new ProductosEntity(3, "Producto 3", "Descripcion 3", 300.0, "Categoria 3",3, 30, true),
                                   new ProductosEntity(201,"Producto 201","Descripcion 201",201.0,"Categoria 201",4,201,true)
                                    );

        mockedListResponse = mockedListEntity.stream()
            .map(productosService.mapToResponse)
            .toList();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readAll() {

        // Mock the repository call
        Mockito.when(productosRepository.findAll()).thenReturn(mockedListEntity);

        // Call the service method
        List<ProductosResponse> response = productosService.readAll();

        // Assertions
        assertNotNull(response);
        assertEquals(mockedListResponse.size(), response.size());

        for (int i = 0; i < mockedListResponse.size(); i++) {
            assertEquals(mockedListResponse.get(i).getId(), response.get(i).getId());
            assertEquals(mockedListResponse.get(i).getNombre(), response.get(i).getNombre());
            assertEquals(mockedListResponse.get(i).getDescripcion(), response.get(i).getDescripcion());
            assertEquals(mockedListResponse.get(i).getPrecio(), response.get(i).getPrecio());
            assertEquals(mockedListResponse.get(i).getCategoria(), response.get(i).getCategoria());
            assertEquals(mockedListResponse.get(i).getStock(), response.get(i).getStock());
        }


    }

    @Test
    void readAllDoesNotExists(){

        // Mock the repository call to return an empty list
        Mockito.when(productosRepository.findAll()).thenReturn(List.of());

        // Call the service method
        List<ProductosResponse> response = productosService.readAll();

        // Assertions
        assertNotNull(response);
        assertTrue(response.isEmpty(), "The response should be an empty list when no products exist.");

    }


    @Test
    void readById() {

        // Mock the repository call
        Mockito.when(productosRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockedListEntity.get(0)));

        // Call the service method
        ProductosResponse response = productosService.readById(1);

        // Assertions
        assertNotNull(response);
        assertEquals(mockedListResponse.get(0).getId(), response.getId());
        assertEquals(mockedListResponse.get(0).getNombre(), response.getNombre());
        assertEquals(mockedListResponse.get(0).getDescripcion(), response.getDescripcion());
        assertEquals(mockedListResponse.get(0).getPrecio(), response.getPrecio());
        assertEquals(mockedListResponse.get(0).getCategoria(), response.getCategoria());
        assertEquals(mockedListResponse.get(0).getStock(), response.getStock());

    }

    @Test
    void readByIdDoesNotExists() {

        // Mock the repository call to return an empty Optional
        Mockito.when(productosRepository.findById(999)).thenReturn(Optional.empty());

        // Call the service method
        ProductosResponse response = productosService.readById(999);

        // Assertions
        assertNull(response, "The response should be null when the product does not exist.");

    }

    @Test
    void create() throws Exception {

        // Mock the repository call
        Mockito.when(productosRepository.save(mockedListEntity.get(0))).thenReturn(mockedListEntity.get(0));

        // Call the service method
        ProductosResponse response = productosService.create(mockedListEntity.get(0));

        // Assertions
        assertNotNull(response);
        assertEquals(mockedListResponse.get(0).getId(), response.getId());
        assertEquals(mockedListResponse.get(0).getNombre(), response.getNombre());
        assertEquals(mockedListResponse.get(0).getDescripcion(), response.getDescripcion());
        assertEquals(mockedListResponse.get(0).getPrecio(), response.getPrecio());
        assertEquals(mockedListResponse.get(0).getCategoria(), response.getCategoria());
        assertEquals(mockedListResponse.get(0).getStock(), response.getStock());

    }

    @Test
    void createThrowsException() {

        // Mock the repository call to throw an exception
        Mockito.when(productosRepository.save(Mockito.any(ProductosEntity.class))).thenThrow(new RuntimeException("Error creating product"));

        // Call the service method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            productosService.create(mockedListEntity.get(0));
        });

        // Assertions
        assertNotNull(exception);
        assertEquals("Error al crear el producto: Error creating product", exception.getMessage());

    }

    @Test
    void update() throws Exception {

        // Mock the repository call
        Mockito.when(productosRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockedListEntity.get(0)));
        Mockito.when(productosRepository.save(mockedListEntity.get(0))).thenReturn(mockedListEntity.get(0));

        // Call the service method
        ProductosResponse response = productosService.update(mockedListEntity.get(0));

        // Assertions
        assertNotNull(response);
        assertEquals(mockedListResponse.get(0).getId(), response.getId());
        assertEquals(mockedListResponse.get(0).getNombre(), response.getNombre());
        assertEquals(mockedListResponse.get(0).getDescripcion(), response.getDescripcion());
        assertEquals(mockedListResponse.get(0).getPrecio(), response.getPrecio());
        assertEquals(mockedListResponse.get(0).getCategoria(), response.getCategoria());
        assertEquals(mockedListResponse.get(0).getStock(), response.getStock());

    }

    @Test
    void updateDoesNotExists() throws  Exception {

        ProductosEntity mockedEntity = new ProductosEntity(999, "Producto 999", "Descripcion 999", 999.0, "Categoria 999", 5, 50, true);

        // Mock the repository call to return an empty Optional
        Mockito.when(productosRepository.findById(999)).thenReturn(Optional.empty());

        // Call the service method and expect an exception

        ProductosResponse productosResponse = productosService.update(mockedEntity);

        // Assertions
        assertNull(productosResponse);

    }

    @Test
    void updateThrowsException() {

        // Mock the repository call to throw an exception
        Mockito.when(productosRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockedListEntity.get(0)));
        Mockito.when(productosRepository.save(Mockito.any(ProductosEntity.class))).thenThrow(new RuntimeException("Error updating product"));

        // Call the service method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            productosService.update(mockedListEntity.get(0));
        });

        // Assertions
        assertNotNull(exception);
        assertEquals("Error al actualizar el producto: Error updating product", exception.getMessage());

    }


    @Test
    void deleteById() {

        // Mock the repository call
        Mockito.when(productosRepository.findById(1)).thenReturn(Optional.ofNullable(mockedListEntity.get(0)));

        // Call the service method
        String response = productosService.deleteById(1);

        // Assertions
        assertNotNull(response);
        assertEquals("Producto con ID " + mockedListEntity.get(0).getId() + " ha sido eliminado.", response);

    }

    @Test
    void deleteByIdDoesNotExists() {

        // Mock the repository call to return an empty Optional
        Mockito.when(productosRepository.findById(999)).thenReturn(Optional.empty());

        // Call the service method
        String response = productosService.deleteById(999);

        // Assertions
        assertNull(response, "The response should be null when the product does not exist.");

    }


    @Test
    void queryByCategoria() {

        // Mock the repository call
        Mockito.when(productosRepository.queryByCategoria("Categoria 1")).thenReturn(mockedListEntity.stream()
                .filter(p -> p.getCategoria().equals("Categoria 1"))
                .toList());

        // Call the service method
        List<ProductosResponse> response = productosService.queryByCategoria("Categoria 1");

        // Assertions
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(mockedListResponse.get(0).getId(), response.get(0).getId());
        assertEquals(mockedListResponse.get(0).getNombre(), response.get(0).getNombre());

    }

    @Test
    void queryByCategoriaDoesNotExists() {

        // Mock the repository call to return an empty list
        Mockito.when(productosRepository.queryByCategoria("Categoria Inexistente")).thenReturn(List.of());

        // Call the service method
        List<ProductosResponse> response = productosService.queryByCategoria("Categoria Inexistente");

        // Assertions
        assertNotNull(response);
        assertTrue(response.isEmpty(), "The response should be an empty list when no products exist for the category.");

    }

    @Test
    void findByProveedor() {

        // Mock the repository call
        Mockito.when(productosRepository.findByProveedor(1)).thenReturn(mockedListEntity.stream()
                .filter(p -> p.getProveedor().equals(1))
                .toList());

        // Call the service method
        List<ProductosResponse> response = productosService.findByProveedor(1);

        // Assertions
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(mockedListResponse.get(0).getId(), response.get(0).getId());
        assertEquals(mockedListResponse.get(0).getNombre(), response.get(0).getNombre());

    }

    @Test
    void findByProveedorDoesNotExists() {

        // Mock the repository call to return an empty list
        Mockito.when(productosRepository.findByProveedor(999)).thenReturn(List.of());

        // Call the service method
        List<ProductosResponse> response = productosService.findByProveedor(999);

        // Assertions
        assertNotNull(response);
        assertTrue(response.isEmpty(), "The response should be an empty list when no products exist for the provider.");

    }

}