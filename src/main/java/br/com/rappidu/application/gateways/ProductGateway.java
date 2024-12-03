package br.com.rappidu.application.gateways;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;

import java.util.List;

public interface ProductGateway {

    void save(Product product);

    List<Product> findByType(ProductType productTypeResponse);

    void remove (Long id);

    void update (Long id, Product product);

    List<Product> all();
}
