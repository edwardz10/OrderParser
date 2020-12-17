package com.cardpay.orderparser.exception;

import lombok.Data;

import java.util.List;

@Data
public class ParseException extends Exception {

    private String errorMessage;
    private List<String> errorDetails;

    public ParseException(String errorMessage, List<String> errorDetails) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
