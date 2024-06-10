package dev.mmartins.fcamarachallenge.application.domain.usecases.customer;

import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomersHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationUseCase {
    private final ProductsHttpClient productsHttpClient;
    private final CustomersHttpClient customersHttpClient;

    public RecommendationUseCase(final ProductsHttpClient productsHttpClient, final CustomersHttpClient customersHttpClient) {
        this.productsHttpClient = productsHttpClient;
        this.customersHttpClient = customersHttpClient;
    }
    public Recommendation execute(final String document) {
        final List<ProductsResponse> products = this.productsHttpClient.getProducts();
        final List<CustomerResponse> customers = this.customersHttpClient.getCustomers();
        return new Recommendation(document, customers, products);
    }
}
