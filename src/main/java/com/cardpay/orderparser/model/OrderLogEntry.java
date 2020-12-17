package com.cardpay.orderparser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLogEntry {
    private Long id;
    private Double amount;
    private String comment;
    private String fileName;
    private long lineNumber;
    private String result;
}
