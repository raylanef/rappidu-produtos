package br.com.rappidu.infra.gateways;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import br.com.rappidu.infra.persistence.ProductRepository;
import br.com.rappidu.infra.persistence.entities.ProductEntity;
import br.com.rappidu.infra.persistence.mappers.ProductEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryGatewayTest {

    @Mock
    private ProductEntityMapper mapper;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductRepositoryGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE);  // Mock do Product
        ProductEntity entity = new ProductEntity(); // Mock do ProductEntity

        when(mapper.toEntity(product)).thenReturn(entity);

        // Act
        gateway.save(product);

        // Assert
        verify(mapper, times(1)).toEntity(product);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testFindByType() {
        // Arrange
        ProductType productType = ProductType.LANCHE; // Mock do ProductType
        ProductEntity entity = new ProductEntity(); // Mock de ProductEntity
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE); // Mock do Product

        when(repository.findByType(productType.getCode())).thenReturn(List.of(entity));
        when(mapper.toModel(entity)).thenReturn(product);

        // Act
        List<Product> result = gateway.findByType(productType);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));

        verify(repository, times(1)).findByType(productType.getCode());
        verify(mapper, times(1)).toModel(entity);
    }

    @Test
    void testRemove() {
        // Arrange
        Long id = 1L;

        // Act
        gateway.remove(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        // Arrange
        Long id = 1L;
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE);// Mock do Product
        ProductEntity existingEntity = new ProductEntity(); // Entidade existente
        ProductEntity updatedEntity = new ProductEntity(); // Entidade atualizada

        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(mapper.merge(product, existingEntity)).thenReturn(updatedEntity);

        // Act
        gateway.update(id, product);

        // Assert
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).merge(product, existingEntity);
        verify(repository, times(1)).save(updatedEntity);
    }

    @Test
    void testAll() {
        // Arrange
        ProductEntity entity = new ProductEntity(); // Mock de ProductEntity
        Product product = new Product(1L, "Hamburguer", new BigDecimal("10.0"), ProductType.LANCHE);; // Mock do Product

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toModel(entity)).thenReturn(product);

        // Act
        List<Product> result = gateway.all();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toModel(entity);
    }
}