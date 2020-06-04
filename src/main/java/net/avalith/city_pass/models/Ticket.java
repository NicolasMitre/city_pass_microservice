package net.avalith.city_pass.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Ticket {
    @NotNull
    private String code;
}
