package dev.mmartins.fcamarachallenge.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SortedPurchaseByPrice {
    @JsonProperty("compras")
    private Set<Purchase> purchases;

    public SortedPurchaseByPrice(final List<CustomerResponse> customers, final List<ProductsResponse> products) {
        final Comparator<Purchase> comparator = Comparator.comparingDouble(Purchase::totalPrice);
        this.purchases = new TreeSet<>(comparator);

        final var productMap = products.stream()
                .collect(Collectors.toMap(ProductsResponse::id, product -> product));

        customers.forEach(customer -> {
            final var list = customer.purchases().stream()
                    .filter(cp -> productMap.containsKey(cp.id()))
                    .map(purchasesResponse -> {
                        ProductsResponse product = productMap.get(purchasesResponse.id());
                        if (product == null) {
                            throw new IllegalStateException("Product not found for id: " + purchasesResponse.id());
                        }
                        return new Product(purchasesResponse.id(), purchasesResponse.count(), product.wineType(), product.price(), product.harvest(), product.purchaseDate());
                    })
                    .collect(Collectors.toList());

            double totalPrice = list.stream()
                    .mapToDouble(x -> x.price() * x.count())
                    .sum();

            this.purchases.add(new Purchase(customer.document(), customer.name(), list, totalPrice));
        });
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }
}
