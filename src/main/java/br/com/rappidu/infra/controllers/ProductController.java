package br.com.rappidu.infra.controllers;

import br.com.rappidu.application.usecases.CreateProductUseCase;
import br.com.rappidu.application.usecases.FindProductUseCase;
import br.com.rappidu.infra.controllers.dto.request.ProductRequest;
import br.com.rappidu.infra.controllers.dto.responses.ProductResponse;
import br.com.rappidu.infra.mappers.ProductMapper;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.processing.Find;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@ComponentScan(
        basePackages = "br.com.rappidu.application.usecases",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {CreateProductUseCase.class,
                           FindProductUseCase.class}
        )
)
public class ProductController {

    private final ProductMapper mapper;
    private final CreateProductUseCase createProductUseCase;
    private final FindProductUseCase findProductUseCase;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listByType(@RequestParam(name = "type") ProductType productType) {
        List<Product> products = findProductUseCase.findByType(productType);
        return ResponseEntity.ok(products.stream()
                .map(mapper::toResponse).collect(Collectors.toList()));

    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody ProductRequest productRequest){
        Product product = mapper.toModel(productRequest);
        createProductUseCase.create(product);
        return new ResponseEntity<>("Product Created", HttpStatus.CREATED);
    }

}
