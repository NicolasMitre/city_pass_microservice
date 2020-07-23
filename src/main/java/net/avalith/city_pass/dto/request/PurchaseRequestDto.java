package net.avalith.city_pass.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequestDto {
    @NotEmpty(message = "There has to be at least one product bought")
    private List<@Valid TicketRequestDto> products;

    @Min(value = 1)
    private Integer userId;

    @PastOrPresent(message = "The purchase date must be today's date or a previous one")
    private LocalDateTime purchaseDate;
}