package net.avalith.city_pass.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPurchasesResponseDto {
    @JsonProperty("purchases")
    private Map<LocalDateTime,List<PurchaseResponseDto>> purchasesMap;

    public static ListPurchasesResponseDto fromListPurchases (List<PurchaseResponseDto> purchases) {

        return ListPurchasesResponseDto.builder()
                .purchasesMap(purchases.stream()
                .collect(Collectors.groupingBy(PurchaseResponseDto::getPurchaseDate)))
                .build();
    }
}
