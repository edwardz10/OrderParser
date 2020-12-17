package com.cardpay.orderparser;

import com.cardpay.orderparser.exception.ValidationException;
import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.ValidationService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private OrderParserContainer parserContainer;

    @Override
    public void run(String... args) throws Exception {
        try {
            Set<String> argsSet = Sets.newHashSet(args);
            validationService.validate(argsSet);

            parserContainer.parse(argsSet);
        } catch (ValidationException ve) {
            log.warn(ve.getErrorMessage());
            ve.getErrorDetails().forEach(System.err::println);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
