package com.cardpay.orderparser.service;

import com.cardpay.orderparser.exception.ValidationException;

import java.util.Set;

public interface ValidationService {
    void validate(Set<String> args) throws ValidationException;
}
