package net.avalith.city_pass.paypal.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount {
    @JsonProperty
    private String currency;
    @JsonProperty
    private String total;
}
