package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.Excursion;
import net.avalith.city_pass.models.ExcursionTicket;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ExcursionTicketResponseDto extends TicketResponseDto {
    private String excursionName;

    public static ExcursionTicketResponseDto createExcursionTicketResponseDto(ExcursionTicket excursionTicket) {
        Excursion excursion = excursionTicket.getExcursion();

        return ExcursionTicketResponseDto.builder()
                .productType(excursionTicket.getTicketType().name())
                .excursionName(excursion.getName())
                .cityName(excursion.getCity().getName())
                .quantity(excursionTicket.getQuantity())
                .unitPrice(excursionTicket.getUnitPrice())
                .subTotal(excursionTicket.getSubTotal())
                .code(excursionTicket.getCode())
                .build();
    }
}
