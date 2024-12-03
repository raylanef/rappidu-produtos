package br.com.rappidu.infra.mappers;

import br.com.rappidu.infra.controllers.dto.request.ProductRequest;
import br.com.rappidu.infra.controllers.dto.responses.ProductResponse;
import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductRequest product);
    ProductResponse toResponse(Product product);

    default ProductType typeToModel(Integer code) {
        return ProductType.getByCode(code);
    }

    default String typeToResponseName(ProductType type) {
        return type.getName();
    }
}
