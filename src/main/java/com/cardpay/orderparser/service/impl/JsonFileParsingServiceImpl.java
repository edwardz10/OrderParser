package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.model.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.cardpay.orderparser.util.ParseUtil.jsonToOrder;

@Service
@Qualifier("json")
public class JsonFileParsingServiceImpl extends TextFileParsingService {

    @Override
    protected Order parseLine(String line) throws Exception {
        return jsonToOrder(line);
    }

    @Override
    public String fileExtension() {
        return "json";
    }
}
