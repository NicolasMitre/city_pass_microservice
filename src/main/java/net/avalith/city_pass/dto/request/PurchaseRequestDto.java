package net.avalith.city_pass.dto.request;

import net.avalith.city_pass.dto.TicketDto;

import java.util.List;

public class PurchaseRequestDto {
    private List<TicketDto> products;
    private Integer userId;
}