package dev.mmartins.fcamarachallenge.application.http.products;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(name = "products", url = "${feign.products.url}")
public interface ProductsHttpClient {

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    List<ProductsResponse> getProducts();
}
