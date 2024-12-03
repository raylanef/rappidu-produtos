package br.com.rappidu.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ProductType {
    LANCHE(1, "Lanche"), ACOMPANHAMENTO(2, "Acompanhamento"),
    BEBIDA(3, "Bebida"), SOBREMESA(4, "Sobremesa");

    private final Integer code;
    private final String name;

    private static final ProductType[] VALUES = values();

    public static ProductType getByCode(Integer code) {
       return Stream.of(VALUES)
               .filter(v -> v.code.equals(code))
               .findAny()
               .orElseThrow();
    }
}
