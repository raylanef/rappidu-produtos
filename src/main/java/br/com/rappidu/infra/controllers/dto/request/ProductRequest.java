package br.com.rappidu.infra.controllers.dto.request;


import java.math.BigDecimal;

public record ProductRequest(String name,
                             BigDecimal price,
                             Integer type) {
}
