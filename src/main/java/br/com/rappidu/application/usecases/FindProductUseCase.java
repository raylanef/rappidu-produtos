package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.ProductGateway;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindProductUseCase {

    private final ProductGateway repository;

    public List<Product> findByType(ProductType productTypeResponse) {
        return repository.findByType(productTypeResponse);
    }

    public List<Product> all() {
        return repository.all();
    }

}
