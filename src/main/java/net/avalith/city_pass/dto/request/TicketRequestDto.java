package net.avalith.city_pass.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.city_pass.models.enums.TicketType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequestDto {
    @Min(value = 1)
    private Integer idProduct;

    @NotNull
    @JsonBackReference
    private TicketType ticketType;

    @Min(value = 1, message = "Quantity must be 1 or higher")
    private Integer quantity;
}
