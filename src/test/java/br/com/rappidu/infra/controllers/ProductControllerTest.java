package br.com.rappidu.infra.controllers;

import br.com.rappidu.application.usecases.CreateProductUseCase;
import br.com.rappidu.application.usecases.FindProductUseCase;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import br.com.rappidu.infra.controllers.dto.request.ProductRequest;
import br.com.rappidu.infra.controllers.dto.responses.ProductResponse;
import br.com.rappidu.infra.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    private ProductMapper mapper;

    @Mock
    private CreateProductUseCase createProductUseCase;

    @Mock
    private FindProductUseCase findProductUseCase;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfProductsByType() {
        // Arrange
        ProductType productType = ProductType.LANCHE;
        Product product1 = new Product(1L, "Pizza", new BigDecimal(10.0), productType);
        Product product2 = new Product(2L, "Burger",new BigDecimal(8.0) ,productType);

        ProductResponse response1 = new ProductResponse(1L, "Pizza",  new BigDecimal(10.0), productType.toString());
        ProductResponse response2 = new ProductResponse(2L, "Burger", new BigDecimal(8.0) ,productType.toString());

        when(findProductUseCase.findByType(productType)).thenReturn(Arrays.asList(product1, product2));
        when(mapper.toResponse(product1)).thenReturn(response1);
        when(mapper.toResponse(product2)).thenReturn(response2);

        // Act
        ResponseEntity<List<ProductResponse>> response = productController.listByType(productType);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Pizza", response.getBody().get(0).name());
        verify(findProductUseCase, times(1)).findByType(productType);
        verify(mapper, times(1)).toResponse(product1);
        verify(mapper, times(1)).toResponse(product2);
    }

    @Test
    void shouldRegisterProductSuccessfully() {
        // Arrange
        ProductRequest productRequest = new ProductRequest("Soda", new BigDecimal(2.0) , 3);
        Product product = new Product(null, "Soda",new BigDecimal(2.0) , ProductType.BEBIDA);

        when(mapper.toModel(productRequest)).thenReturn(product);

        // Act
        ResponseEntity<String> response = productController.register(productRequest);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Product Created", response.getBody());
        verify(mapper, times(1)).toModel(productRequest);
        verify(createProductUseCase, times(1)).create(product);
    }
}