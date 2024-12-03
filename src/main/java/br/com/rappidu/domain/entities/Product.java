package br.com.rappidu.domain.entities;

import java.math.BigDecimal;

public record Product(Long code,
                      String name,
                      BigDecimal price,
                      ProductType type) {
}
