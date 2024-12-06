package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.ProductGateway;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import br.com.rappidu.infra.persistence.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindProductUseCaseTest {

    @Mock
    private ProductGateway repository;

    @InjectMocks
    private FindProductUseCase findProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByType() {
        // Arrange
        ProductType productType = ProductType.LANCHE; // Mock do ProductType
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE);

        when(repository.findByType(productType)).thenReturn(List.of(product));

        // Act
        List<Product> result = findProductUseCase.findByType(productType);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));

        verify(repository, times(1)).findByType(productType);
    }

    @Test
    void testAll() {
        // Arrange
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE); // Mock do Produto

        when(repository.all()).thenReturn(List.of(product));

        // Act
        List<Product> result = findProductUseCase.all();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));

        verify(repository, times(1)).all();
    }
}