package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.exception.ParseException;
import com.cardpay.orderparser.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.cardpay.orderparser.util.ParseUtil.parseJsonToOrder;

@Service
@Qualifier("json")
public class JsonFileParsingServiceImpl extends TextFileParsingService {

    @Override
    protected Order parseLine(String line) throws Exception {
        return parseJsonToOrder(line);
    }

    @Override
    public String fileExtension() {
        return "json";
    }
}
