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
public class Item {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "quantity")
    private String quantity;
    @JsonProperty(value = "price")
    private String price;
    @JsonProperty(value = "currency")
    private String currency;
}
