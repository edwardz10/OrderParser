package com.cardpay.orderparser.service;

import com.cardpay.orderparser.enums.FileFormatEnum;

public interface OrderGenerator {
    void generateOrders(Integer size, FileFormatEnum format);
}
