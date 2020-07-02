package net.avalith.city_pass.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseResponseDto {
    List<TicketResponseDto> products;
    private LocalDateTime purchaseDate;
}
