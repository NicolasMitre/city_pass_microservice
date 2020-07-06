package net.avalith.city_pass.services;

import lombok.RequiredArgsConstructor;
import net.avalith.city_pass.dto.request.PurchaseRequestDto;
import net.avalith.city_pass.dto.request.TicketRequestDto;
import net.avalith.city_pass.dto.response.ListPurchasesResponseDto;
import net.avalith.city_pass.dto.response.PurchaseResponseDto;
import net.avalith.city_pass.dto.response.TicketResponseDto;
import net.avalith.city_pass.dto.response.factory.TicketResponseDtoFactory;
import net.avalith.city_pass.exceptions.PurchaseNotFoundException;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.Ticket;
import net.avalith.city_pass.models.User;
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

    @Transactional
    public Purchase processPurchase(PurchaseRequestDto purchaseDto) {
        Purchase purchase = Purchase.builder()
                .purchaseDate(purchaseDto.getPurchaseDate())
                .user(userService.getById(purchaseDto.getUserId()))
                .build();

        purchase = purchaseRepository.save(purchase);
        List<Ticket> tickets = new ArrayList<>();

        for (TicketRequestDto ticketRequestDto : purchaseDto.getProducts()) {
            Ticket newTicket = ticketService.processTicket(ticketRequestDto, purchase);
            tickets.add(newTicket);
        }

        Double totalPrice = tickets
                .stream()
                .map(Ticket::getSubTotal)
                .reduce(0d, Double::sum);

        purchase.setTotalPrice(totalPrice);
        purchase.setProductsBought(tickets);

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
