package dev.mmartins.fcamarachallenge.entrypoint.rest;

import dev.mmartins.fcamarachallenge.application.domain.usecases.customer.GetLoyaltyCustomersUseCase;
import dev.mmartins.fcamarachallenge.application.domain.LoyaltyCustomers;
import dev.mmartins.fcamarachallenge.application.domain.usecases.customer.Recommendation;
import dev.mmartins.fcamarachallenge.application.domain.usecases.customer.RecommendationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customers")
@RestController
public class CustomersController {
    private final GetLoyaltyCustomersUseCase getLoyaltyCustomersUseCase;
    private final RecommendationUseCase recommendationUseCase;

    public CustomersController(final GetLoyaltyCustomersUseCase getLoyaltyCustomersUseCase, final RecommendationUseCase recommendationUseCase) {
        this.getLoyaltyCustomersUseCase = getLoyaltyCustomersUseCase;
        this.recommendationUseCase = recommendationUseCase;
    }

    @GetMapping("/loyalty")
    public ResponseEntity<LoyaltyCustomers> loyalty() {
        return ResponseEntity.ok(getLoyaltyCustomersUseCase.execute());
    }

    @GetMapping("/{document}/recommendation")
    public ResponseEntity<Recommendation> recommendation(@PathVariable final String document) {
        return ResponseEntity.ok(recommendationUseCase.execute(document));
    }
}
