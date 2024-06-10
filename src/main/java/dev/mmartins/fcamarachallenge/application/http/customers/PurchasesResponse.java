package dev.mmartins.fcamarachallenge.application.http.customers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchasesResponse(
        @JsonProperty("codigo") Long id,
        @JsonProperty("quantidade") Long count
) {
}
