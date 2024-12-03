package br.com.rappidu.infra.persistence.mappers;

import br.com.rappidu.domain.entities.Product;
import br.com.rappidu.domain.entities.ProductType;
import br.com.rappidu.infra.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =
        NullValuePropertyMappingStrategy.IGNORE)
public interface ProductEntityMapper {

    ProductEntity toEntity(Product product);

    @Mapping(source = "id", target = "code")
    Product toModel(ProductEntity product);

    ProductEntity merge(Product model, @MappingTarget ProductEntity entity);

    default Integer typeToEntity(ProductType productType) {
        return productType.getCode();
    }

    default ProductType typeToModel(Integer productType) {
        return ProductType.getByCode(productType);
    }
}
