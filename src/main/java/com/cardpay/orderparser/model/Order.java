package com.cardpay.orderparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @JsonProperty("orderId")
    private Long id;
    private Double amount;
    private String currency;
    private String comment;
}
