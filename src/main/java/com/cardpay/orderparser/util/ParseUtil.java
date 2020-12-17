package com.cardpay.orderparser.util;

import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.model.OrderLogEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class ParseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    public static String orderToJson(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public static String orderLogEntryToJson(OrderLogEntry orderLogEntry) {
        try {
            return objectMapper.writeValueAsString(orderLogEntry);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    public static Order jsonToOrder(String line) throws Exception {
        return objectMapper.readValue(line, Order.class);
    }

    public static Order csvToOrder(String line) throws Exception {
        String[] tokens = line.split(",");
        Long id = null;
        Double amount = null;

        try {
            id = Long.valueOf(tokens[0]);
            amount = Double.valueOf(tokens[1]);
        } catch (NumberFormatException e) {}

        return new Order(id, amount, tokens[2], tokens[3]);
    }

    public static String orderToCsv(Order order) {
        return order.getId() + ","
                + order.getAmount() + ","
                + order.getCurrency() + "," + order.getComment();
    }

}
