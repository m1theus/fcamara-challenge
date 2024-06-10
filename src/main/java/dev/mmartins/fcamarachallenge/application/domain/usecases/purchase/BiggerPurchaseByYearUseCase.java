package dev.mmartins.fcamarachallenge.application.domain.usecases.purchase;

import dev.mmartins.fcamarachallenge.application.domain.BiggerPurchaseByYear;
import dev.mmartins.fcamarachallenge.application.domain.SortedPurchaseByPrice;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomerResponse;
import dev.mmartins.fcamarachallenge.application.http.customers.CustomersHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsHttpClient;
import dev.mmartins.fcamarachallenge.application.http.products.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BiggerPurchaseByYearUseCase {
    private final ProductsHttpClient productsHttpClient;
    private final CustomersHttpClient customersHttpClient;

    public BiggerPurchaseByYearUseCase(final ProductsHttpClient productsHttpClient, final CustomersHttpClient customersHttpClient) {
        this.productsHttpClient = productsHttpClient;
        this.customersHttpClient = customersHttpClient;
    }

    public BiggerPurchaseByYear execute(final String year) {
        final List<ProductsResponse> products = productsHttpClient.getProducts();
        final List<CustomerResponse> customers = customersHttpClient.getCustomers();
        return new BiggerPurchaseByYear(year, new SortedPurchaseByPrice(customers, products));
    }

}
