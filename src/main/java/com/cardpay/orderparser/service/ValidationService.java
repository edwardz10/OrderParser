package com.cardpay.orderparser.service;

import com.cardpay.orderparser.exception.ValidationException;

import java.util.List;

public interface ValidationService {
    void validate(List<String> args) throws ValidationException;
}
