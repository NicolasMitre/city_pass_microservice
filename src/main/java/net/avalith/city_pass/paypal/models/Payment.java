package net.avalith.city_pass.paypal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    private String id;
    private String intent;
    private String state;
    private Payer payer;
    private List<Transaction> transactions;
    @JsonProperty(value = "create_time")
    private LocalDateTime createTime;
    @JsonProperty(value = "links")
    private List<Link> links;

}
