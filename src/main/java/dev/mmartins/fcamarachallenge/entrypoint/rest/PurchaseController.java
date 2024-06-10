package dev.mmartins.fcamarachallenge.entrypoint.rest;

import dev.mmartins.fcamarachallenge.application.domain.SortedPurchaseByPrice;
import dev.mmartins.fcamarachallenge.application.domain.BiggerPurchaseByYear;
import dev.mmartins.fcamarachallenge.application.domain.usecases.BiggerPurchaseByYearUseCase;
import dev.mmartins.fcamarachallenge.application.domain.usecases.GetPurchaseUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/purchases")
@RestController
public class PurchaseController {
    private final GetPurchaseUseCase getPurchaseUseCase;
    private final BiggerPurchaseByYearUseCase biggerPurchaseByYearUseCase;

    public PurchaseController(final GetPurchaseUseCase getPurchaseUseCase, final BiggerPurchaseByYearUseCase biggerPurchaseByYearUseCase) {
        this.getPurchaseUseCase = getPurchaseUseCase;
        this.biggerPurchaseByYearUseCase = biggerPurchaseByYearUseCase;
    }

    @GetMapping
    public ResponseEntity<SortedPurchaseByPrice> purchases() {
        return ResponseEntity.ok(getPurchaseUseCase.execute());
    }

    @GetMapping("/{year}")
    public ResponseEntity<BiggerPurchaseByYear> biggerPurchaseByYear(@PathVariable final String year) {
        return ResponseEntity.ok(biggerPurchaseByYearUseCase.execute(year));
    }
}
