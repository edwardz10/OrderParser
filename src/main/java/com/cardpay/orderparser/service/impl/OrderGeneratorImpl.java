package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.enums.CurrencyEnum;
import com.cardpay.orderparser.enums.FileFormatEnum;
import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.service.OrderGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.cardpay.orderparser.util.ParseUtil.orderToCsv;
import static com.cardpay.orderparser.util.ParseUtil.orderToJson;

@Service
@Slf4j
public class OrderGeneratorImpl implements OrderGenerator {

    private final Random random = new Random();

    @Override
    public void generateOrders(Integer size, FileFormatEnum format) {
        List<Order> orders = IntStream.rangeClosed(1, size)
                .mapToObj(this::randomOrder)
                .collect(Collectors.toList());

        String fileName = randomFileName(format);
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            orders.forEach(order -> {
                try {
                    fileWriter.write(getStringRepresentation(order, format) + System.lineSeparator());
                } catch (IOException e) {
                    log.error("Failed to write an order: {}", e.getLocalizedMessage());
                }
            });

            log.info("File {} with {} order in {} format has been generated",
                    fileName, size, format);
        } catch (IOException e) {
            log.error("Failed to generate an orders file: {}", e.getLocalizedMessage());
        }
    }

    private Order randomOrder(long id) {
        return new Order(id, random.nextDouble()*1000d, randomCurrency(), "Generated");
    }

    private String randomCurrency() {
        return CurrencyEnum.values()[random.nextInt(CurrencyEnum.values().length)].name();
    }

    private String randomFileName(FileFormatEnum format) {
        return "orders" + System.currentTimeMillis() + "." + format.getFormat();
    }

    private String getStringRepresentation(Order order, FileFormatEnum format) {
        switch (format) {
            case CSV: return orderToCsv(order);
            case JSON: return orderToJson(order);
            default: return "";
        }
    }
}
