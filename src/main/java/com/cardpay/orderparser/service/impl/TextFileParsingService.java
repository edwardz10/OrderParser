package com.cardpay.orderparser.service.impl;

import com.cardpay.orderparser.exception.ParseException;
import com.cardpay.orderparser.model.Order;
import com.cardpay.orderparser.util.ParseUtil;
import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class TextFileParsingService extends BaseFileParsingService {

    @Override
    public void parse(String filePath) throws ParseException {
        try {
            File file = new File(filePath);
            String fileName = file.getName();
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            Streams.mapWithIndex(lines.stream(),
                    (str, index) -> {
                        Order order = null;
                        try {
                            order = parseLine(str);
                        } catch (Exception e) {
                            log.error("Failed to parse {}: {}", str, e.getLocalizedMessage());
                        }
                        return convert(order, index, fileName);
                    }).forEach(orderLogEntry -> System.out.println(ParseUtil.orderLogEntryToJson(orderLogEntry)));
        } catch (IOException e) {
            log.error("Failed to read {}: {}", filePath, e.getLocalizedMessage());
        }
    }

    protected abstract Order parseLine(String line) throws Exception;
}
