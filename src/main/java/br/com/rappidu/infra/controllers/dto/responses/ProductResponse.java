package br.com.rappidu.infra.controllers.dto.responses;

import java.math.BigDecimal;


public record ProductResponse(Long code,
                              String name,
                              BigDecimal price,
                              String type) {

}
