package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.avalith.city_pass.models.CityPass;
import net.avalith.city_pass.models.CityPassTicket;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CityPassTicketResponseDto extends TicketResponseDto {
    private String cityPassName;
    private Integer quantityDays;

    public static CityPassTicketResponseDto createCityPassTicketResponseDto(CityPassTicket cityPassTicket) {
        CityPass cityPass = cityPassTicket.getCityPass();
        return CityPassTicketResponseDto.builder()
                .productType(cityPassTicket.getTicketType().name())
                .cityName(cityPass.getCity().getName())
                .quantity(cityPassTicket.getQuantity())
                .unitPrice(cityPassTicket.getUnitPrice())
                .subTotal(cityPassTicket.getSubTotal())
                .code(cityPassTicket.getCode())
                .cityPassName(cityPass.getName())
                .quantityDays(cityPass.getDays())
                .build();
    }
}
