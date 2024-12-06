package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.ProductGateway;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {

    @Mock
    private ProductGateway repository;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        // Arrange
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE);  // Mock do Produto

        // Act
        createProductUseCase.create(product);

        // Assert
        verify(repository, times(1)).save(product);
    }
}