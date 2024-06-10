package dev.mmartins.fcamarachallenge.application.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiggerPurchaseByYear {
    private final Purchase purchase;

    public BiggerPurchaseByYear(final String year, final SortedPurchaseByPrice sortedPurchaseByPrice) {
        final Map<Purchase, List<Product>> purchaseProductMap = new HashMap<>();

        sortedPurchaseByPrice
                .getPurchases()
                .forEach(p -> {
                    final var productsList = p
                            .products()
                            .stream()
                            .filter(product -> product.purchaseDate().equals(year))
                            .toList();
                    purchaseProductMap.put(p, productsList);
                });


        this.purchase = purchaseProductMap
                .keySet()
                .stream().max(Comparator.comparing(Purchase::totalPrice))
                .orElse(null);
    }

    public Purchase getPurchase() {
        return purchase;
    }
}
