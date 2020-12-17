package com.cardpay.orderparser;

import com.cardpay.orderparser.exception.ValidationException;
import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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
            List<String> argsList = Arrays.asList(args);
            validationService.validate(argsList);

            parserContainer.parse(argsList);
        } catch (ValidationException ve) {
            log.warn(ve.getErrorMessage());
            ve.getErrorDetails().forEach(System.err::println);
        }
    }

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(Application.class, args);
        log.info("APPLICATION FINISHED");
    }
}
