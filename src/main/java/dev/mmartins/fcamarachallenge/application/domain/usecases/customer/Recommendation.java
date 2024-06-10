package dev.mmartins.fcamarachallenge.application.domain.usecases.customer;

import dev.mmartins.fcamarachallenge.application.domain.Product;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recommendation {
    private final Product recommendation;

    public Recommendation(final String customerDocument,
                          final List<CustomerResponse> customers,
                          final List<ProductsResponse> products) {

        final var productsMap = products.stream().collect(Collectors.toMap(ProductsResponse::id, p -> p));

        final var customer = customers
                .stream()
                .filter(customerResponse -> customerResponse.document().equals(customerDocument))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Customer not found!"));

        final List<Product> productList = customer
                .purchases()
                .stream()
                .filter(p -> productsMap.containsKey(p.id()))
                .map(p -> {
                    ProductsResponse productsResponse = productsMap.get(p.id());
                    return new Product(p.id(), p.count(), productsResponse.wineType(), productsResponse.price(), productsResponse.harvest(), productsResponse.purchaseDate());
                }).toList();


        this.recommendation = productList
                .stream()
                .max(Comparator.comparing(Product::wineType))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid wine type"));
    }

    public Product getRecommendation() {
        return recommendation;
    }
}
