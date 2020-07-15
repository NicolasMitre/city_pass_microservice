package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TicketResponseDto {
    private String productType;
    private String cityName;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;
    private String code;
}