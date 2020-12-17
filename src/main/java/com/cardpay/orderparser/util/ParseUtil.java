package com.cardpay.orderparser.util;

import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.model.OrderLogEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ParseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String orderLogEntryToJson(OrderLogEntry orderLogEntry) {
        try {
            return objectMapper.writeValueAsString(orderLogEntry);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public static Order parseJsonToOrder(String line) throws Exception {
        return objectMapper.readValue(line, Order.class);
    }

    public static Order parseCsvToOrder(String line) throws Exception {
        String[] tokens = line.split(",");
        return new Order(Long.valueOf(tokens[0]), Double.valueOf(tokens[1]), tokens[2], tokens[3]);
    }
}
