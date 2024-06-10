package dev.mmartins.fcamarachallenge.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Products(
        @JsonProperty("codigo") Long id,
        @JsonProperty("quantidade") Long count,
        @JsonProperty("tipo_vinho") String wineType,
        @JsonProperty("preco") Double price,
        @JsonProperty("safra") String harvest,
        @JsonProperty("ano_compra") String purchaseDate
) {
}
