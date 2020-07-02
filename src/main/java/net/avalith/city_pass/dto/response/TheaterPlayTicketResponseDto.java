package net.avalith.city_pass.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterPlayTicketResponseDto extends TicketResponseDto{
    private String theaterName;
    private String theaterPlayName;
    private LocalDateTime playDate;
}

