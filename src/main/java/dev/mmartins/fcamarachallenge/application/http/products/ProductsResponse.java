package dev.mmartins.fcamarachallenge.application.http.products;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductsResponse(
        @JsonProperty("codigo") Long id,
        @JsonProperty("tipo_vinho") String wineType,
        @JsonProperty("preco") Double price,
        @JsonProperty("safra") String harvest,
        @JsonProperty("ano_compra") String purchaseDate
) {
}
