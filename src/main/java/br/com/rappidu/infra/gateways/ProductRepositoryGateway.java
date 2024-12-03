package br.com.rappidu.infra.gateways;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import br.com.rappidu.application.gateways.ProductGateway;
import br.com.rappidu.infra.persistence.entities.ProductEntity;
import br.com.rappidu.infra.persistence.mappers.ProductEntityMapper;
import br.com.rappidu.infra.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductRepositoryGateway implements ProductGateway {

    private final ProductEntityMapper mapper;
    private final ProductRepository repository;

    @Override
    public void save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        repository.save(entity);
    }

    @Override
    public List<Product> findByType(ProductType productType) {
        var listProductsEntities = repository.findByType(productType.getCode());

        return listProductsEntities.stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Product product) {
        var entity = repository.findById(id);
        var entityUpdated = mapper.merge(product, entity.get());
        repository.save(entityUpdated);
    }

    @Override
    public List<Product> all() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
