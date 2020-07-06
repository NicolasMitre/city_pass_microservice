package net.avalith.city_pass.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.PurchaseRequestDto;
import net.avalith.city_pass.dto.response.ListPurchasesResponseDto;
import net.avalith.city_pass.dto.response.PurchaseResponseDto;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.services.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/{idPurchase}")
    public ResponseEntity<PurchaseResponseDto> getPurchasesByIdPurchase(@PathVariable(name = "idPurchase") Integer idPurchase) {
        Purchase purchase = purchaseService.findById(idPurchase);

        return ResponseEntity.ok(purchaseService.convertToDto(purchase));
    }


    @GetMapping("/client/{idClient}")
    public ResponseEntity<ListPurchasesResponseDto> getPurchasesByIdClient(@PathVariable(name = "idClient") Integer idClient) {
        List<Purchase> purchases = purchaseService.findByIdClient(idClient);

        return (purchases.size() > 0) ? ResponseEntity.ok(purchaseService.convertToDto(purchases))
                : ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<PurchaseResponseDto> savePurchase(@Valid @RequestBody PurchaseRequestDto purchaseDto) {
        Purchase purchase = purchaseService.processPurchase(purchaseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.convertToDto(purchase));
    }
}