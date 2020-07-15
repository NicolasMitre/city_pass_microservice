package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.Purchase;
import net.avalith.city_pass.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponseDto {
    private List<TicketResponseDto> products;
    private LocalDateTime purchaseDate;
    private Integer userId;
    private String clientName;
    private String username;
    private Double total;

    public static PurchaseResponseDto fromPurchase(Purchase purchase, List<TicketResponseDto> ticketResponseDto) {
        User user = purchase.getUser();
        return PurchaseResponseDto.builder()
                .userId(user.getIdUser())
                .clientName(user.getName())
                .username(user.getUsername())
                .products(ticketResponseDto)
                .total(purchase.getTotalPrice())
                .purchaseDate(purchase.getPurchaseDate())
                .build();
    }
}
