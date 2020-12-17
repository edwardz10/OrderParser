package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.model.OrderLogEntry;
import com.cardpay.orderparser.service.FileParsingService;

public abstract class BaseFileParsingService implements FileParsingService {

    protected OrderLogEntry convert(Order order, long lineNumber, String fileName) {
        return order == null
            ? null
            : new OrderLogEntry(order.getId(),
                order.getAmount(),
                order.getComment(),
                fileName,
                lineNumber + 1,
                "OK");
    }
}
