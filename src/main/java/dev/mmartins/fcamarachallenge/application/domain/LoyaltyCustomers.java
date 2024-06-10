package dev.mmartins.fcamarachallenge.application.domain;

import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoyaltyCustomers {
    private final LinkedHashSet<Purchase> loayaltycustomers;

    public LoyaltyCustomers(final List<CustomerResponse> customers, List<ProductsResponse> products) {
        this.loayaltycustomers = new LinkedHashSet<>();
        final var productsMap = products.stream().collect(Collectors.toMap(ProductsResponse::id, ProductsResponse::purchaseDate));
        final var productsMapById = products.stream().collect(Collectors.toMap(ProductsResponse::id, p -> p));


        final List<CustomerResponse> recurringCustomers = new ArrayList<>();

        customers.forEach(customer -> {
            final Set<String> yearsOfPurchaseDate = new HashSet<>();

            customer.purchases().forEach(purchase -> {
                String purchaseDate = productsMap.get(purchase.id());
                if (purchaseDate != null) {
                    yearsOfPurchaseDate.add(purchaseDate);
                }
            });


            if (yearsOfPurchaseDate.size() > 1) {
                recurringCustomers.add(customer);
            }
        });

        recurringCustomers.sort(Comparator.comparingInt(CustomerResponse::purchaseSize).reversed());

        recurringCustomers
                .subList(0, 3)
                .forEach(customer -> {
                    final var list = customer.purchases().stream()
                            .filter(cp -> productsMapById.containsKey(cp.id()))
                            .map(purchasesResponse -> {
                                ProductsResponse product = productsMapById.get(purchasesResponse.id());
                                if (product == null) {
                                    throw new IllegalStateException("Product not found for id: " + purchasesResponse.id());
                                }
                                return new Product(purchasesResponse.id(), purchasesResponse.count(), product.wineType(), product.price(), product.harvest(), product.purchaseDate());
                            })
                            .collect(Collectors.toList());

                    double totalPrice = list.stream()
                            .mapToDouble(x -> x.price() * x.count())
                            .sum();
                    loayaltycustomers.add(new Purchase(customer.document(), customer.name(), list, totalPrice));
                });


    }

    public Set<Purchase> getLoayaltycustomers() {
        return loayaltycustomers;
    }
}
