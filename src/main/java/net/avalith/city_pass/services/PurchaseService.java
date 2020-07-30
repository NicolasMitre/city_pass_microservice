package net.avalith.city_pass.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.PurchaseRequestDto;
import net.avalith.city_pass.dto.response.ListPurchasesResponseDto;
import net.avalith.city_pass.dto.response.PurchaseResponseDto;
import net.avalith.city_pass.dto.response.TicketResponseDto;
import net.avalith.city_pass.dto.response.factory.TicketResponseDtoFactory;
import net.avalith.city_pass.exceptions.PurchaseNotFoundException;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.User;
import net.avalith.city_pass.models.enums.PurchaseStatus;
import net.avalith.city_pass.paypal.PayPalApi;
import net.avalith.city_pass.paypal.models.Payment;
import net.avalith.city_pass.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final TicketService ticketService;
    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final TicketResponseDtoFactory ticketResponseDtoFactory;
    private final PayPalApi payPalApi;

    @Transactional
    public Payment processPurchase(PurchaseRequestDto purchaseDto) throws JsonProcessingException {

        List<Ticket> tickets = purchaseDto.getProducts()
                .stream()
                .map(ticketRequestDto -> ticketService.processTicket(ticketRequestDto, purchaseDto.getPurchaseDate()))
                .collect(Collectors.toList());

        Double totalPrice = tickets.stream()
                .map(Ticket::getSubTotal)
                .reduce(0d, Double::sum);

        Purchase purchasePersisted = purchaseRepository.save(Purchase.builder()
                .status(PurchaseStatus.PENDING)
                .user(userService.getById(purchaseDto.getUserId()))
                .purchaseDate(purchaseDto.getPurchaseDate())
                .productsBought(tickets)
                .totalPrice(totalPrice)
                .build());

        tickets.forEach(ticket -> ticket.setPurchase(purchasePersisted));
        ticketService.persistTicket(tickets);

        return payPalApi.createPayment(purchasePersisted);
    }

    @Transactional
    public String executePayment(Integer idPurchase, String paymentId, String payerId) throws JsonProcessingException {
        String response = payPalApi.executePayment(paymentId, payerId);

        Purchase purchase = findById(idPurchase);
        purchase.setStatus(PurchaseStatus.COMPLETED);
        persistPurchase(purchase);

        List<Ticket> productsBought = purchase.getProductsBought();
        productsBought.forEach(ticket -> ticket.setTicketStatus(PurchaseStatus.COMPLETED));
        ticketService.persistTicket(purchase.getProductsBought());

        return response;
    }


    @Transactional
    public void cancelPurchase(Integer idPurchase) {
        Purchase purchase = findById(idPurchase);
        purchase.setStatus(PurchaseStatus.CANCELED);
        persistPurchase(purchase);
        purchase.getProductsBought().forEach(ticket -> ticket.setTicketStatus(PurchaseStatus.CANCELED));
        ticketService.persistTicket(purchase.getProductsBought());
    }

    private Purchase persistPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase findById(Integer idPurchase) {
        return purchaseRepository.findById(idPurchase)
                .orElseThrow(PurchaseNotFoundException::new);
    }

    public List<Purchase> findByIdClient(Integer idClient) {
        User user = userService.getById(idClient);
        return purchaseRepository.findAllByIdClient(user.getIdUser());
    }

    public PurchaseResponseDto convertToDto(Purchase purchase) {
        List<TicketResponseDto> listTicketResponseDto = purchase.getProductsBought()
                .stream()
                .map(ticket -> ticketResponseDtoFactory.getTicketResponseDto(ticket, purchase))
                .collect(Collectors.toList());

        return PurchaseResponseDto.fromPurchase(purchase, listTicketResponseDto);
    }

    public ListPurchasesResponseDto convertToDto(List<Purchase> purchases) {
        List<PurchaseResponseDto> listPurchaseResponseDto = new ArrayList<>();
        purchases.stream()
                .map(this::convertToDto)
                .forEach(listPurchaseResponseDto::add);

        return ListPurchasesResponseDto.fromListPurchases(listPurchaseResponseDto);
    }

}
