package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.model.OrderLogEntry;
import com.cardpay.orderparser.service.FileParsingService;
import org.springframework.util.StringUtils;

public abstract class BaseFileParsingService implements FileParsingService {

    protected OrderLogEntry convert(Order order, long lineNumber, String fileName) {
        return order == null
            ? null
            : new OrderLogEntry(order.getId(),
                order.getAmount(),
                order.getCurrency(),
                order.getComment(),
                fileName,
                lineNumber + 1,
                getConversionResult(order));
    }

    private String getConversionResult(Order order) {
        if (order.getAmount() == null) {
            return "Amount not defined";
        }

        if (StringUtils.isEmpty(order.getCurrency())) {
            return "Currency not defined";
        }

        return "OK";
    }
}
