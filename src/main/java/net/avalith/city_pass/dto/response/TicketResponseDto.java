package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDto {
    private String cityName;
    private Float subTotal;
    private Integer quantity;
    private String code;
}

