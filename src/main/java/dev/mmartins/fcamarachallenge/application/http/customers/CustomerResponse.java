package dev.mmartins.fcamarachallenge.application.http.customers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CustomerResponse(
        @JsonProperty("nome") String name,
        @JsonProperty("cpf") String document,
        @JsonProperty("compras") List<PurchasesResponse> purchases
) {
    public int purchaseSize() {
        return this.purchases.size();
    }
}
