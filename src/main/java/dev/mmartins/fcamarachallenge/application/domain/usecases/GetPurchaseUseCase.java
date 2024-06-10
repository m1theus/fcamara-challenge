package dev.mmartins.fcamarachallenge.application.domain.usecases;

import dev.mmartins.fcamarachallenge.application.domain.SortedPurchaseByPrice;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomersHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPurchaseUseCase {
    private final ProductsHttpClient productsHttpClient;
    private final CustomersHttpClient customersHttpClient;

    public GetPurchaseUseCase(final ProductsHttpClient productsHttpClient, final CustomersHttpClient customersHttpClient) {
        this.productsHttpClient = productsHttpClient;
        this.customersHttpClient = customersHttpClient;
    }

    public SortedPurchaseByPrice execute() {
        final List<ProductsResponse> products = productsHttpClient.getProducts();
        final List<CustomerResponse> customers = customersHttpClient.getCustomers();
        return new SortedPurchaseByPrice(customers, products);
    }
}
