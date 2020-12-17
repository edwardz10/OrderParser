package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.cardpay.orderparser.util.ParseUtil.csvToOrder;

@Service
@Qualifier("csv")
@Slf4j
public class CsvFileParsingServiceImpl extends TextFileParsingService {

    @Override
    public String fileExtension() {
        return "csv";
    }

    @Override
    protected Order parseLine(String line) throws Exception {
        return csvToOrder(line);
    }
}
