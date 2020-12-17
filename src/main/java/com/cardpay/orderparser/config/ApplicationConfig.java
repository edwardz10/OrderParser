package com.cardpay.orderparser.config;

import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.FileParsingService;
import com.cardpay.orderparser.service.impl.OrderParserContainerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Autowired
    @Qualifier("csv")
    private FileParsingService csvFileParsingService;

    @Autowired
    @Qualifier("json")
    private FileParsingService jsonFileParsingService;

    @Bean
    public OrderParserContainer parserContainer() {
        Map<String, FileParsingService> orderParsingServices = new HashMap<>();
        orderParsingServices.put(csvFileParsingService.fileExtension(), csvFileParsingService);
        orderParsingServices.put(jsonFileParsingService.fileExtension(), jsonFileParsingService);
        return new OrderParserContainerImpl(orderParsingServices);
    }
}
