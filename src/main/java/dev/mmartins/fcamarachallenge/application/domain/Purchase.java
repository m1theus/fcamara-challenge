package dev.mmartins.fcamarachallenge.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Purchase(
        @JsonProperty("cpf") String document,
        @JsonProperty("nome") String name,
        @JsonProperty("produtos") java.util.List<Products> products,
        @JsonProperty("preco_total") Double totalPrice
) {
}
