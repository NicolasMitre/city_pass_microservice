package net.avalith.city_pass.paypal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @JsonProperty(value = "amount")
    private Amount amount;

    @JsonProperty(value = "item_list")
    private ItemList itemList;
}
