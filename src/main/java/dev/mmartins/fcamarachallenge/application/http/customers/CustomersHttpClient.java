package dev.mmartins.fcamarachallenge.application.http.customers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "customers", url = "${feign.customers.url}")
public interface CustomersHttpClient {

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    List<CustomerResponse> getCustomers();
}
