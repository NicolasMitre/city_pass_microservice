package net.avalith.city_pass.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.PurchaseRequestDto;
import net.avalith.city_pass.dto.response.ListPurchasesResponseDto;
import net.avalith.city_pass.dto.response.PurchaseResponseDto;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.paypal.models.Payment;
import net.avalith.city_pass.services.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return (!purchases.isEmpty()) ? ResponseEntity.ok(purchaseService.convertToDto(purchases))
                : ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<Payment> savePurchase(@Valid @RequestBody PurchaseRequestDto purchaseDto) throws JsonProcessingException {
        return ResponseEntity.ok(purchaseService.processPurchase(purchaseDto));
    }

    @GetMapping(value = "/pay/success/{idPurchase}")
    public ResponseEntity<String> successPay(@PathVariable(name = "idPurchase") Integer idPurchase,
                                             @RequestParam("paymentId") String paymentId,
                                             @RequestParam("token") String token,
                                             @RequestParam("PayerID") String payerId) throws JsonProcessingException {

        return ResponseEntity.ok(purchaseService.executePayment(idPurchase,paymentId, payerId));
    }

    @GetMapping(value = "/pay/failure/{idPurchase}")
    public ResponseEntity<String> failurePay(@PathVariable(name = "idPurchase") Integer idPurchase,
                                             @RequestParam("token") String token) {
        //todo reintegrar items
        return ResponseEntity.ok("F por tu compra");
    }
}