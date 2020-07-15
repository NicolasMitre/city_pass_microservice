package net.avalith.city_pass.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequestDto {
    private List<TicketRequestDto> products;
    private Integer userId;
    private LocalDateTime purchaseDate;
}