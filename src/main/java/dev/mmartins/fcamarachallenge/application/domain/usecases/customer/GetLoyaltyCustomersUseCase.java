package dev.mmartins.fcamarachallenge.application.domain.usecases.customer;

import dev.mmartins.fcamarachallenge.application.domain.LoyaltyCustomers;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomersHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetLoyaltyCustomersUseCase {
    private final ProductsHttpClient productsHttpClient;
    private final CustomersHttpClient customersHttpClient;

    public GetLoyaltyCustomersUseCase(ProductsHttpClient productsHttpClient, CustomersHttpClient customersHttpClient) {
        this.productsHttpClient = productsHttpClient;
        this.customersHttpClient = customersHttpClient;
    }

    public LoyaltyCustomers execute() {
        final List<ProductsResponse> products = this.productsHttpClient.getProducts();
        final List<CustomerResponse> customers = this.customersHttpClient.getCustomers();
        return new LoyaltyCustomers(customers, products);
    }
}
