package br.com.rappidu.application.usecases;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.application.gateways.ProductGateway;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreateProductUseCase {

    private final ProductGateway repository;

    
    public void create(Product product) {
        repository.save(product);
    }
}
