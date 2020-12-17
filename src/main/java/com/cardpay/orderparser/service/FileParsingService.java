package com.cardpay.orderparser.service;

import com.cardpay.orderparser.exception.ParseException;

public interface FileParsingService {
    void parse(String filePath) throws ParseException;
    String fileExtension();
}
