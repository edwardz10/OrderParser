package com.cardpay.orderparser;

import com.cardpay.orderparser.enums.FileFormatEnum;
import com.cardpay.orderparser.exception.ValidationException;
import com.cardpay.orderparser.service.OrderGenerator;
import com.cardpay.orderparser.service.OrderParserContainer;
import com.cardpay.orderparser.service.ValidationService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    private ValidationService validationService;
    @Autowired
    private OrderParserContainer parserContainer;
    @Autowired
    private OrderGenerator orderGenerator;

    @Override
    public void run(String... args) throws Exception {
        Options options = new Options();
        options.addOption(Option.builder("g")
                .longOpt("generate")
                .hasArg(false)
                .required(false)
                .argName("generate an orders' file")
                .build());
        options.addOption(Option.builder("s")
                .longOpt("size")
                .hasArg(true)
                .required(false)
                .argName("size of an order' file")
                .build());
        options.addOption(Option.builder("f")
                .longOpt("format")
                .hasArg(true)
                .required(false)
                .argName("file format")
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("g")) {
                runGenerateFlow(cmd);
            } else {
                runParseFlow(args);
            }
        } catch (ParseException e) {
            log.error("Failed to parse arguments: {}", Arrays.asList(args));
        }
    }

    private void runGenerateFlow(CommandLine cmd) {
        Integer size = 100;
        FileFormatEnum format = null;
        String formatStr = null;
        String sizeStr = null;

        if (cmd.hasOption("s")) {
            try {
                sizeStr = cmd.getOptionValue("s");
                size = Integer.valueOf(sizeStr);
            } catch (NumberFormatException e) {
                log.warn("Failed to read option 'size': {}", e.getLocalizedMessage());
                log.warn("Default size is {}", size);
                size = 100;
            }
        }

        if (cmd.hasOption("f")) {
            formatStr = cmd.getOptionValue("f");
            format = FileFormatEnum.fromString(formatStr);
        }

        if (format == null) {
            log.warn("Failed to read option 'file': {}", formatStr);
        } else {
            orderGenerator.generateOrders(size, format);
        }
    }

    private void runParseFlow(String[] args) {
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
